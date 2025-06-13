SERVICES = basketservice configserver identityservice orderservice productservice

.PHONY: build
build:
	@for service in $(SERVICES); do \
		echo "Building $$service..."; \
		(cd $$service && ./mvnw compile jib:dockerBuild); \
	done

up: build
	docker compose up -d

down:
	docker compose down