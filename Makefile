default: dp

db:
	docker-compose -f docker-postgres-db/compose.yml up

db-clean:
	docker-compose -f docker-postgres-db/compose.yml down -v --remove-orphans

