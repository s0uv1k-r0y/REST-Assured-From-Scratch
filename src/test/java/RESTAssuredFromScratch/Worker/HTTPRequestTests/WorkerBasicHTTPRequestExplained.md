The WorkerBasicHTTPRequest.java class consists of all the Basic HTTP request methods GET, POST, PUT, PATCH, DELETE.

The following actions are covered in the mentioned methods:

1. getMethod - To fetch all the records

2. getSpecificRecord - To fetch specific record

3. getSpecificRecordUsingDataProvider - To get records using "dataProviderMethod"

4. postMethod_Dynamic - To create record using values passed through parameters

5. postMethod_static - To create record from hardcoded JSON passed

6. putMethod - To update record

7. patchMethod - To patch record

8. patchMethodUsingJsonPath - This method accepts specific item and update the "designation" captured using JsonPath.

9. deleteMethod - To delete record

10. dataProviderMethod - To send ID values to "getSpecificRecordUsingDataProvider"