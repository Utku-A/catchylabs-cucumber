


Test Koşum
```` mvn
mvn test -Dcucumber.tags="@web" 
mvn test -Dcucumber.tags="@mobile"
mvn test -Dcucumber.tags="@api" 
````


Farklı tarayıcılar ile test koşum
````mvn
mvn test -Dcucumber.tags="@web" -Dbrowser=chrome 
mvn test -Dcucumber.tags="@mobile" -Dbrowser=firefox 
mvn test -Dcucumber.tags="@web" -Dbrowser=edge 
````


Rapor görüntüleme
```` sh
allure serve allure-results/
````

