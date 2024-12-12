# File Service Server

## Overview

This project is a robust Spring Boot application designed to handle secure file upload and download operations. It provides a scalable, containerized solution for file management with comprehensive deployment options.

## Features

- **Secure File Upload**: Upload files using API key authentication
- **Easy File Download**: Retrieve files by filename
- **Docker Containerization**: Consistent deployment across environments
- **Kubernetes Support**: Scalable and reliable deployment
- **Flexible Configuration**: Environment-specific settings

## Prerequisites

- **Java Development Kit (JDK) 17**
- **Gradle**
- **Docker**
- **Kubernetes** (for cluster deployment)

## Project Structure

Key components of the project:

- **`FileController.java`**: Handles HTTP requests for file operations
- **`FileServiceApplication.java`**: Spring Boot application entry point
- **`application.properties`**: Configuration management
- **`Dockerfile`**: Docker containerization script
- **`build.gradle.kts`**: Gradle build configuration
- **Kubernetes Manifests**: Deployment, Service, and Ingress configurations

## Deployment Steps

### 1. Build the Application

Use the Gradle wrapper to clean and build the project:

```bash
./gradlew clean build
```

### 2. Run Locally

Start the application locally:

```bash
./gradlew bootRun
```

The application will run on `http://localhost:8080` by default.

### 3. Docker Deployment

#### Build Docker Image

```bash
docker build -t file-service:1.0 .
```

#### Run Docker Container

```bash
docker run -p 8080:8080 --name file-service-container file-service:1.0
```

### 4. Kubernetes Deployment

#### Apply Kubernetes Manifests

```bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
kubectl apply -f k8s/ingress.yaml
```

The application will be accessible via the Ingress controller.

---

## API Endpoints

### File Upload

**POST** `/files`
- Requires `Celonis-Auth` header with API key
- Attach file in the request body

```bash
curl -X POST -H "Celonis-Auth: your_api_key" -F file=@/path/to/file http://localhost:8080/files
```

### File Download

**GET** `/files/{filename}`

```bash
curl -O http://localhost:8080/files/your_filename
```

---

## Configuration

Configure the application using `application.properties`:

- `celonis.api-key`: API key for authentication
- `file.storage.path`: Directory for file storage

---

## Expected Outputs

1. **Local Deployment**:
   - Application runs on `http://localhost:8080`.
   - Files can be uploaded and downloaded via API endpoints.

2. **Docker Deployment**:
   - Runs the application in a container accessible on `http://localhost:8080`.

3. **Kubernetes Deployment**:
   - The application is deployed as pods in the cluster, accessible via the Ingress endpoint.
   - Persistent volumes ensure file data is retained across restarts.

---

## Production Improvements and Scalability

To make the application fully production-ready, the following enhancements have been identified:

### Security Enhancements
- **Secrets Management**: Use Kubernetes Secrets to manage sensitive data like the API key securely.
- **Authentication & Authorization**: Implement OAuth2 or JWT for more robust security.
- **HTTPS Support**: Use TLS termination in Ingress to ensure secure communication.

### Scalability and Reliability
- **Horizontal Pod Autoscaling (HPA)**: Configure Kubernetes HPA to auto-scale application pods based on resource usage.
- **Readiness and Liveness Probes**: Add health checks in `deployment.yaml` to monitor pod health and availability.
- **Zero-Downtime Deployments**: Use `RollingUpdate` strategy in Kubernetes to prevent disruptions during deployments.

### Monitoring and Logging
- **Centralized Logging**: Use solutions like ELK (Elasticsearch, Logstash, and Kibana) for analyzing application logs.
- **Metrics and Alerts**: Integrate monitoring tools like Prometheus and Grafana for resource metrics and alerting.

### Data Persistence
- **Persistent Volume Claims (PVCs)**: Ensure file data is stored on durable volumes across pod restarts.
- **Backup Strategy**: Schedule regular backups to external storage like AWS S3 or Google Cloud Storage.

### Code Improvements
- **Environment Configurations**: Use ConfigMaps or Spring Profiles for environment-specific settings.
- **Error Handling**: Improve exception handling for better client responses and debugging.

---

## Key Implementations for Task 3: Improvements

We have implemented several features to align with production-ready standards:

1. **Zero-Downtime Deployment**:
   - RollingUpdate strategy is configured in the Kubernetes deployment manifest. In the current setup, Rolling Update is the default update strategy in Kubernetes deployments, it is explicitly configured in the deployment.yaml file of the project. Kubernetes deployments inherently use the RollingUpdate strategy unless otherwise specified.

2. **Data Persistence**:
   - PersistentVolumeClaim is configured in `pvc.yaml` to ensure data persistence across pod restarts.

3. **Ingress Controller**:
   - The application is exposed via Ingress, providing flexibility for domain-based routing and HTTPS support.

---

## Multi-Realm Deployment Challenges and Solution

### Challenges
- **Environment-Specific Configurations**: Different database hosts and external service configurations for each realm.
- **Consistency Across Realms**: Ensuring the same application versions and configurations across realms.
- **Scaling and Monitoring**: Managing performance and scaling for diverse cloud environments.

### Solution

1. **Centralized CI/CD Pipeline**:
   - Unified CI/CD with modular stages for environment-specific configurations.

2. **Configuration Management**:
   - Store configuration files in a secure repository and inject them dynamically during deployment.

3. **Observability**:
   - Use Prometheus and Grafana for centralized monitoring.
   - Employ Fluentd and ELK Stack for logging across realms.

---

