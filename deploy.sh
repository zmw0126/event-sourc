#!/usr/bin/env bash

set -e
set -x

export REGISTRY=10.11.0.6:5000

mvn clean

mvn package -D docker.image.prefix=${REGISTRY}  -D maven.test.skip=true 

docker push ${REGISTRY}/kbastani-hystrix-dashboard       
docker push ${REGISTRY}/kbastani-shopping-cart-service   
docker push ${REGISTRY}/kbastani-edge-service            
docker push ${REGISTRY}/kbastani-catalog-service         
docker push ${REGISTRY}/kbastani-catalog-service             
docker push ${REGISTRY}/kbastani-online-store-web             
docker push ${REGISTRY}/kbastani-discovery-service            
docker push ${REGISTRY}/kbastani-config-service               
docker push ${REGISTRY}/kbastani-payment-service              
docker push ${REGISTRY}/kbastani-user-service                 
docker push ${REGISTRY}/kbastani-account-service              
docker push ${REGISTRY}/kbastani-order-service                
docker push ${REGISTRY}/kbastani-inventory-service  

# curl -X DELETE "http://10.11.0.4:20081/v1/applications/alauda/event" \
# -H 'Content-Type: application/json' \
# -H 'Authorization: Token 3051d656b998939335d0c25c3fb9c9ae0c6a6610' \
# -d '{ }' 

# echo  

# sleep 15

# curl -X POST  \
# -H "Content-Type: multipart/form-data;charset=UTF-8"  \
# -H "Cache-Control: no-cache"   \
# -H 'Authorization: Token 3051d656b998939335d0c25c3fb9c9ae0c6a6610' \
# -F "services=@./docker-compose.alauda.yml" \
# -F "app_name=event"   \
# -F "region=alauda17"   \
# -F "space_name=global"   \
# "http://10.11.0.4:20081/v1/applications/alauda"

# echo


