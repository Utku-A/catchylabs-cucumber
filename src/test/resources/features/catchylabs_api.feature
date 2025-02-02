@api
Feature: Catchylabs Api Test

  @smoke
  Scenario: Api login
    Given Login isteği atılır ve token bilgisi kaydedilir
    When Session isteği atılır ve kullanıcının aktif olduğu doğrulanır
    Then Account isteğinde başarılı sonuç geldiği kontrol edilir


  @smoke
  Scenario: Api Hesaba para ekleme
    Given Login isteği atılır ve token bilgisi kaydedilir
    And Account isteği ile bakiye ve diğer bilgiler kaydedilir
    And  Hesapa "<amount>" tutarında "Utku Aladağ" tarafından para yüklemesi için istek atılır
    When Son işlemlerde "<amount>" tutarında "Utku Aladağ" tarafından para yatırıldığı doğrulanır
    Then Hesap bakiyesi "<amount>" artığı kontrol edilir
    Examples:
      | amount |
      | 100    |
      | 200    |


  @smoke
  Scenario Outline: Hesap adını değiştirme
    Given Login isteği atılır ve token bilgisi kaydedilir
    And Account isteği ile bakiye ve diğer bilgiler kaydedilir
    When Hesap adı "<account_name>" ismi ile değiştirilir
    Then Yeni hesap adının "<account_name>" olduğu doğrulanır
    Examples:
      | account_name |
      | Test Hesap 1 |
      | Test Hesap 2 |


  @smoke
  Scenario Outline: Api Hesaba para transferi
    Given Login isteği atılır ve token bilgisi kaydedilir
    And Account isteği ile bakiye ve diğer bilgiler kaydedilir
    And  "<account_name>" Hesabına "amount" tutarında para transferi için istek atılır
    When Son işlemlerde "amount" tutarında "<account_name>" tarafına transfer doğrulanır
    Then Hesap bakiyesi "amount" azaldığı kontrol edilir
    Examples:
      | account_name | amount |
      | Test Hesap 1 |  100   |
      | Test Hesap 2 |  200   |
