

Test koşum
```` mvn
mvn test -Dbrowser=chrome -Dcucumber.filter.tags="@smoke"
mvn test -Dbrowser=firefox -Dcucumber.filter.tags="@smoke"
````