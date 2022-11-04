Микросервис Склада

Сборка и установка в minikube
1) `gradle build`
2) `docker build -t gurok/arch_store .`
3) `docker push gurok/arch_store`
4) `kubectl create namespace arch-gur`
5) `helm install gorelov-kafka ./deployment/kafka/`   
6) `helm install arch-store ./deployment/app/`
   `kubectl get pods -n arch-gur`

---

Для локального поднятия кафки: `docker-compose-kafka up`

---
### Очистка пространства:

- `helm uninstall arch-order`
- `kubectl delete namespace arch-gur`
- `helm uninstall gorelov-kafka`