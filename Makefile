default: dp

db:
	docker-compose -f docker-postgres-db/compose.yml up

db-clean:
	docker-compose -f docker-postgres-db/compose.yml down -v --remove-orphans

swagger:
	docker-compose -f docker-swagger-ui/compose.yml up

swagger-clean:
	docker-compose -f docker-swagger-ui/compose.yml down -v --remove-orphans

kong:
	docker-compose -f docker-kong/compose.yml up

kong-clean:
	docker-compose -f docker-kong/compose.yml down -v --remove-orphans