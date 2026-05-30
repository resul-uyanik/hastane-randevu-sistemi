# 🏥 Hastane Randevu Sistemi (HRS) Otomasyonu

Bu proje, Spring Boot ve Katmanlı Mimari prensipleri kullanılarak geliştirilmiş, Role-Based Access Control (RBAC) güvenlik altyapısına sahip bir **Hastane Randevu Sistemi** otomasyonudur.

## 🚀 Kullanılan Teknolojiler & Kütüphaneler
* **Backend:** Java 17, Spring Boot 3.x
* **Veritabanı & ORM:** Spring Data JPA, Hibernate, H2 Database (veya MySQL)
* **Güvenlik:** Spring Security (BCryptPasswordEncoder ile RBAC)
* **Validasyon:** Jakarta Validation (DTO Seviyesinde Doğrulama)
* **Frontend:** Thymeleaf, Bootstrap 5, Vanilla JavaScript (Fetch API)

## 📐 Mimari Yapı (Katmanlar)
Proje, hiyerarşik paket düzenine ve gevşek bağlılık (Loose Coupling) ilkesine uygun olarak 5 ana katmanda kurgulanmıştır:
1. **Entity:** Veritabanı tablolarının nesne modelleri (`Appointment`, `Doctor`, `Patient`).
2. **Repository:** Veritabanı CRUD operasyonları ve Derived Query (Türetilmiş Sorgu) metotları.
3. **Service:** İş mantığı, çakışma algoritmaları ve `@Transactional` veri tutarlılık yönetimi.
4. **Controller:** Dışarıdan gelen HTTP isteklerini karşılayan `@RestController` ve sayfa yönlendirmelerini yapan `@Controller` paketleri.
5. **DTO (Data Transfer Object):** İstek nesnelerini validasyon kuralları ile karşılayan ara katman.
6. **Exception:** Projede fırlatılabilecek özel hata sınıflarını ve tüm projede fırlatılan validasyon/iş mantığı hatalarını merkezi olarak yakalayıp frontende anlamlı HTTP durum kodlarıyla dönen`GlobalExceptionHandler`mekanizmasını barındırır.
7. **Config:** Spring Security altyapısını ve Role-Based Access Control (RBAC) yetkilendirme kurallarını (`SecurityConfig`) yöneten yapılandırma bileşenidir.

## 🔒 Güvenlik Kontrolleri (RBAC)
Sistemde iki farklı rol tanımlanmıştır: `USER` ve `ADMIN`. 
* **USER:** Sadece sistemi ve kayıtları görüntüleyebilir (`GET` istekleri).
* **ADMIN:** Randevu oluşturma, doktor/hasta ekleme, güncelleme ve silme yetkilerine sahiptir (`POST`, `PUT`, `PATCH`, `DELETE`).

## 🛠️ Kurulum ve Çalıştırma Rehberi

### Gereksinimler
* Java JDK 17 veya üzeri
* IntelliJ IDEA (veya Eclipse)
* Git

### Çalıştırma Adımları

1. **Projeyi Bilgisayarınıza Klonlayın:**
   ```bash
   git clone https://github.com/resuluyanik/hastane-randevu-sistemi.git
2. **Proje Dizinine Gidin ve Maven Bağımlılıklarını Yükleyin:**
   ```bash
   mvn clean install
3. **Uygulamayı Başlatın:**
   HastaneRandevuSistemiApplication.java sınıfını IDE üzerinden çalıştırın.
4. **Tarayıcıdan Sisteme Bağlanın:**
   Tarayıcınızı açıp http://localhost:8080 adresine gidin.
5. **🔑 Test Giriş Bilgileri**
      **Admin Girişi:**
         Kullanıcı adı:admin
         Şifre:admin123
      **User Girişi:**
         Kullanıcı adı:user
         Şifre:1234