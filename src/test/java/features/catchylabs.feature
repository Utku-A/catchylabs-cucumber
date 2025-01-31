Feature: Catchylabs Test

@smoke
  Scenario: Login
  Given Catchylabs web adresi açılır
  When Hatalı kullanıcı bilgiler ile login olmadığı kontrol edilir
  Then Başarılı kullanıcı girişi yapılır


  @smoke
  Scenario: Hesap Bilgileri Doğrulama
    Given Catchylabs web adresi açılır
    And Başarılı kullanıcı girişi yapılır
    And Test modülü acılır

  @smoke
  Scenario: Hesap Düzenleme
    Given Catchylabs web adresi açılır
    And Başarılı kullanıcı girişi yapılır
    And Test modülü acılır

  @smoke
  Scenario: Para yatırma
    Given Catchylabs web adresi açılır
    And Başarılı kullanıcı girişi yapılır
    And Test modülü acılır


  @smoke
  Scenario: Para Transferi
    Given Catchylabs web adresi açılır
    And Başarılı kullanıcı girişi yapılır
    And Test modülü acılır
