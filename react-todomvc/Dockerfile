# Build stage: install all dependencies and build the static bundle
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

# Runtime stage: only http-server and the built dist
FROM node:20-alpine
WORKDIR /app
RUN npm install -g http-server && npm cache clean --force
COPY --from=build /app/dist ./dist
USER node
EXPOSE 7002
CMD ["http-server", "./dist", "-p", "7002", "-c-1", "--cors"]
