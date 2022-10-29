Микросервис Склада

Сборка и установка в minikube
1) `gradle build`
2) `docker build -t gurok/arch_store .`
3) `docker push gurok/arch_store`
4) `kubectl create namespace arch-gur`
5) `helm install arch-store ./deployment/app/`
   `kubectl get pods -n arch-gur`

---
### Очистка пространства:

- `helm uninstall arch-order`
- `kubectl delete namespace arch-gur`