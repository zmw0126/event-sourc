#!/usr/bin/env bash

set -e

mvn package

docker push 10.11.0.8:5000/kbastani-hystrix-dashboard       
docker push 10.11.0.8:5000/kbastani-shopping-cart-service   
docker push 10.11.0.8:5000/kbastani-edge-service            
docker push 10.11.0.8:5000/kbastani-catalog-service         
docker push 10.11.0.8:5000/kbastani-catalog-service             
docker push 10.11.0.8:5000/kbastani-online-store-web             
docker push 10.11.0.8:5000/kbastani-discovery-service            
docker push 10.11.0.8:5000/kbastani-config-service               
docker push 10.11.0.8:5000/kbastani-payment-service              
docker push 10.11.0.8:5000/kbastani-user-service                 
docker push 10.11.0.8:5000/kbastani-account-service              
docker push 10.11.0.8:5000/kbastani-order-service                
docker push 10.11.0.8:5000/kbastani-inventory-service            