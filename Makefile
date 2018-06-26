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

#  kong-test-api:
#    http POST http://localhost:8001/apis name=httpbin upstream_url=http://httpbin.org uris=/test
#    http http://localhost:8000/test/get
#
#  kong-install-auth-plugin:
#      http POST http://localhost:8001/apis/httpbin/plugins name=key-auth
#
#  kong-consumer:
#      http POST http://localhost:8001/consumers username=yaya
#      http POST http://localhost:8001/consumers/yaya/key-auth key=secret-yaya
#
#      http http://localhost:8000/test/get?apikey=secret-yaya
#
#  kong-install-auth-plugin:
#      http POST http://localhost:8001/apis/httpbin/plugins name=rate-limiting config.minute=5

#
#  jwt
#  http POST http://localhost:8001/apis/httpbin/plugins name=jwt config.secret_is_base64=true
#
#

