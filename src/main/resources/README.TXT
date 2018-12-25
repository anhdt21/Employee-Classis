Cac buoc cu the:

- tao 1 package storage voi cac class va interface can thiet

- tao 1 the input voi name la file trong create.html
  <input type="file" name="file">

- cau hinh thu muc luu anh (location) trong storageProperties

- FileSystemStorageService implement interface StorageService,
  2 cai exception bat loi mac ke no,
  MvcConfig de cau hinh path den thu muc chua anh de lay anh ra.

- them Anotation @EnableConfigurationProperties(StorageProperties.class)
  vao class contructor chua ham main sau do @Bean cai init.

- khai bao column image o cho get image o class Employee.

- vao controller khai bao final cai StorageService.
  tu xem ghi chu o @PostMapping("/create-employee").

- <img th:src="@{/chiuchet/__${e.image}__}" height="200" width="150"/>

--------------------------- ENJOY ---------------------------------