#!/bin/bash

envsubst "\$BACKEND_BASE_PATH" < /temp/default.conf > /etc/nginx/nginx.conf

exec "$@"