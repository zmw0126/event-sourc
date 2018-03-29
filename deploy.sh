#!/usr/bin/env bash

set -e
set -x

# mvn clean

# mvn package -Dmaven.test.skip=true

# docker push 10.11.0.8:5000/kbastani-hystrix-dashboard       
# docker push 10.11.0.8:5000/kbastani-shopping-cart-service   
# docker push 10.11.0.8:5000/kbastani-edge-service            
# docker push 10.11.0.8:5000/kbastani-catalog-service         
# docker push 10.11.0.8:5000/kbastani-catalog-service             
# docker push 10.11.0.8:5000/kbastani-online-store-web             
# docker push 10.11.0.8:5000/kbastani-discovery-service            
# docker push 10.11.0.8:5000/kbastani-config-service               
# docker push 10.11.0.8:5000/kbastani-payment-service              
# docker push 10.11.0.8:5000/kbastani-user-service                 
# docker push 10.11.0.8:5000/kbastani-account-service              
# docker push 10.11.0.8:5000/kbastani-order-service                
# docker push 10.11.0.8:5000/kbastani-inventory-service  

curl -X DELETE "http://10.11.0.4:20081/v1/applications/alauda/event" \
-H 'Content-Type: application/json' \
-H 'Authorization: Token 3051d656b998939335d0c25c3fb9c9ae0c6a6610' \
-d '{ }'   

sleep 30

curl -X POST  \
-H "Content-Type: multipart/form-data;charset=UTF-8"  \
-H "Cache-Control: no-cache"   \
-H 'Authorization: Token 3051d656b998939335d0c25c3fb9c9ae0c6a6610' \
-F "services=./docker-compose.alauda.yml" \
-F "app_name=event"   \
-F "region=alauda17"   \
-F "space_name=global"   \
"http://10.11.0.4:20081/v1/applications/alauda"

