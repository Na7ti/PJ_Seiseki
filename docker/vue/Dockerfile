FROM node:latest

WORKDIR /usr/src/app/

RUN npm install -g npm
# compose up時にコメントアウトしておかないと無限ロードになる
RUN npm install -g @vue/cli

CMD [ "npm", "run", "serve" ]
