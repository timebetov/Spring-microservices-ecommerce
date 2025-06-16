SERVICES = config-service eureka-service basket-service identity-service orders-service products-service gateway-service

.PHONY: build
build:
	@for service in $(SERVICES); do \
		echo "Building $$service..."; \
		(cd $$service && ./mvnw compile jib:dockerBuild); \
	done

up: build
	docker compose up -d

start:
	docker compose up -d

down:
	docker compose down