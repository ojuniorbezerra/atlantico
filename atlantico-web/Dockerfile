### STAGE 1: Build ###
FROM node:10.15.1 AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json /usr/src/app/
RUN npm install && npm install -g @angular/cli@7.0.7
EXPOSE 4200
COPY . /usr/src/app
RUN npm run build
CMD ng serve --host 0.0.0.0 