SERVICES = config-service eureka-service basket-service identity-service orders-service products-service gateway-service
INFRA_SERVICES = ecommerce-rabbitmq products-db orders-db

.PHONY: build
build:
	@for service in $(SERVICES); do \
		echo "Building $$service..."; \
		(cd $$service && ./mvnw compile jib:dockerBuild); \
	done

dev:
	docker compose -f docker-compose.dev.yml up -d
	@$(MAKE) wait-infra

wait-infra:
	@echo "⏳ Waiting for all infrastructure services to become healthy..."
	@for service in $(INFRA_SERVICES); do \
		echo "Waiting for $$service..."; \
		until [ "$$(docker inspect -f '{{.State.Health.Status}}' $$service)" = "healthy" ]; do \
			sleep 2; \
			echo "Still waiting for $$service..."; \
		done; \
		echo "✅ $$service is healthy."; \
	done
	@echo "✅ All infrastructure services are healthy and ready!"

up: build
	docker compose up -d

start:
	docker compose up -d

down:
	docker compose down