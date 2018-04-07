#!/usr/bin/env bash

set -e
set -x

export ONE_BOX=10.11.0.4
export REGION=alauda01
export SPACE_NAME=global
export REGISTRY=10.11.0.6:5000
export ALB_IP=10.11.0.6
export GIT_HOST=10.11.0.5:9988
export NGINX=nginx-10-11-0-6
export AUTH_TOKEN=97898f42b8c95098df3f82037f43bf13fa33ff53


cat kubernetes.alauda.yml | \
sed "s/{{ALB_IP}}/${ALB_IP}/g" | \
sed "s/{{GIT_HOST}}/${GIT_HOST}/g" | \
sed "s/{{REGISTRY}}/${REGISTRY}/g" | \
sed "s/{{REGION}}/${REGION}/g" | \
sed "s/{{NGINX}}/${NGINX}/g"  \
> k8s-tmp.yaml

echo "use k8s-tmp.yaml to deploy the app"

# curl -X DELETE "http://${ONE_BOX}:20081/v1/applications/alauda/event" \
# -H 'Content-Type: application/json' \
# -H "Authorization: Token ${AUTH_TOKEN}" \
# -d '{ }' 

# echo  

# sleep 15

# curl -X POST  \
# -H "Content-Type: multipart/form-data;charset=UTF-8"  \
# -H "Cache-Control: no-cache"   \
# -H "Authorization: Token ${AUTH_TOKEN}" \
# -F "services=@./k8s-tmp.yaml" \
# -F "app_name=event"   \
# -F "region=${REGION}"   \
# -F "space_name=${SPACE_NAME}"   \
# "http://${ONE_BOX}:20081/v1/applications/alauda"

# echo


