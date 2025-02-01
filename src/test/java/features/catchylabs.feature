Feature: Catchylabs Test

@smoke
  Scenario: Login
  Given Catchylabs web adresi açılır
  And Hatalı kullanıcı bilgiler ile login olmadığı kontrol edilir
  When Kullanıcı girişi yapılır
  Then Başarılı giriş yapıldığı doğrulanır.


  @smoke
  Scenario: Hesap bilgileri doğrulama
    Given Catchylabs web adresi açılır
    And Kullanıcı girişi yapılır
    When Test modülü acılır
    Then Hesap bilgileri doğrulanır


  @smoke
  Scenario Outline: Hesap adı düzenleme
    Given Catchylabs web adresi açılır
    And Kullanıcı girişi yapılır
    And Test modülü acılır
    When Hesap adı değiştirme "<account_name>"
    Then Hesap adı doğrulama "<account_name>"
    Examples:
      | account_name |
      | Test Hesap 1 |
      | Test Hesap 2 |


  @smoke
  Scenario Outline: Para yatırma
    Given Catchylabs web adresi açılır
    And Kullanıcı girişi yapılır
    And Test modülü acılır
    When Para yatırma gerçekleştirilir "<amount>"
    Then Hesap bakiyesi kontrol edilir "<control>"
    Examples:
      | amount |
      | 100    |
      | 200    |


  @smoke
  Scenario: Para yatırma validasyon alanları
    Given Catchylabs web adresi açılır
    And Kullanıcı girişi yapılır
    And Test modülü acılır
    Then Para yatırma validasyon alanları kontrol edilir


  @smoke
  Scenario: Para Transferi
    Given Catchylabs web adresi açılır
    And Kullanıcı girişi yapılır
    And Test modülü acılır
