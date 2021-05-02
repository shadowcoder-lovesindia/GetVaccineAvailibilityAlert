# GetVaccineAvailibilityAlert
Auto Alert for vaccine availibility from COWIN Public APIs

**Step 1:Update application.properties**


spring.mail.default-encoding=UTF-8 
spring.mail.host=smtp.gmail.com # -> Put your SMTP Host name 
spring.mail.username=abc@gmail.com # -> If using gmail , please go to google allow settings and allow less security
spring.mail.password=password # -> Password
spring.mail.port=587


spring.mail.protocol=smtp
spring.mail.test-connection=false
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

email.from=abc@gmail.com
email.to=abc@gmail.com
email.cc=abc@gmail.com,abc@gmail.com
email.bcc=abc@gmail.com,abc@gmail.com

spring.cowin.districtidarray= 140,141,142,143,144,145,146,147,148,149,150, 363, 188, 650, 651, 392,505,506,314,265,276,663,664
#choose your respective districts from districtmaster.json file

execution.interval=5
# Put this value in minutes

**Step 2: mvn clean install**

**Step 3:  open cmd and run -> java -jar target/GetVaccineAvailibilityV1-0.0.1-SNAPSHOT.jar**


**Output Email:**
As soon as vaccine appointment is uploaded, Email will content as below --> 


Center Name is::Dr B L Kapur Hospital Site 5 :District::Central Delhi-COVISHIELD	::Available capacity is ::1	::Session date is::02-05-2021	::Minimum age limit is ::18
Center Name is::Manchar SDH(18 To 44 Yrs) :District::Pune-	::Available capacity is ::12	::Session date is::01-05-2021	::Minimum age limit is ::18
Center Name is::Otur PHC (18-44 Years) :District::Pune-	::Available capacity is ::1	::Session date is::04-05-2021	::Minimum age limit is ::18

