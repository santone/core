**Coding Test Assignment: The Net Calculator Service**

Image you're working for a company which provides financial services worldwide.
As a core java backend developer, please develop a new service which allows consumers to
calculate the net price from a gross price.

Pseudo signature of the service:
netPrice = calculateNetPrice(grossPrice, countryIso);

Pseudo examples:
81 = calculateNetPrice(100, DE);
1.6 = calculateNetPrice(1.99, FR);

Your new service needs access to a _taxRateProvider_ which returns for any given country the
current VAT required to calculate the net price. For the purpose of this assignment, you can
represent the tax data used by the _taxRateProvider_ as a simple map of the country to a tax rate.
E.g.
"DE":"0.19", // Germany has 19% VAT on standard taxable goods
"FR":"0.20", // France has 20% VAT on standard taxable goods
...

Please provide a fully runnable (mvn or gradle) and testable java implementation (8 or higher)
along with any required instruction for the reviewer.


** Run the TEST **

You need java 11 to build the project

- It will create a jacoco test coverege, and if it is under than 80% then fail
- It will check the style, code smells, but not strict

```
./gradlew cle build
```

