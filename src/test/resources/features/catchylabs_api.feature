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
    And  Hesapa "amount" tutarında para yüklemesi için istek atılır
    When Son işlemlerde "" tutarında para yatırıldığı doğrulanır
    Then Hesap bakiyesi "amount" artığı kontrol edilir
