SERVICES = config-service eureka-service basket-service identity-service orders-service products-service gateway-service
INFRA_SERVICES = ecommerce-rabbitmq products-db orders-db redis

.PHONY: build
build:
	@for service in $(SERVICES); do \
		echo "Building $$service..."; \
		(cd $$service && ./mvnw compile jib:dockerBuild); \
	done

dev:
	docker compose -f docker-compose.base.yml up -d
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
	docker compose \
		-f docker-compose.base.yml \
		-f docker-compose.ops.yml \
		-f docker-compose.services.yml up -d

start:
	docker compose \
		-f docker-compose.base.yml \
		-f docker-compose.ops.yml \
		-f docker-compose.services.yml up -d

down:
	docker compose \
		-f docker-compose.base.yml \
		-f docker-compose.ops.yml \
		-f docker-compose.services.yml down