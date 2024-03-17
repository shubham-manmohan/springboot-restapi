Summary:
I developed a Spring Boot application featuring four RESTful APIs, complemented with JWT Bearer authentication for
heightened security. The application encompasses:
Signup User API: Users can register by furnishing requisite details such as username, email, and password. Upon
successful registration, a JWT token is generated and provided to facilitate subsequent authentication.
Login User API: Users authenticate themselves by submitting their credentials (username/email and password).
Following successful authentication, a JWT token is generated and issued, granting access to protected endpoints.
Update User API: Authenticated users possess the capability to modify their profile information, encompassing
username, email, or password. This endpoint is safeguarded by JWT authentication, ensuring solely authorized users
can adjust their details.
Third-Party API Integration: A designated API endpoint is established to execute requests to a third-party API
(CoinMarketCap API) for retrieving cryptocurrency data. This endpoint is secured with JWT Bearer authentication,
guaranteeing only authenticated users can access cryptocurrency information.
The application underwent comprehensive testing leveraging the Postman API testing tool to validate both its
functionality and security. Testing protocols included verification of user registration, login, and update
functionalities, alongside confirmation that solely authenticated users could access protected endpoints and retrieve
cryptocurrency data.
Moreover, a screenshot of the application was attached in a Word file to offer a visual representation of its
operation and layout, facilitating a clearer understanding of its functionality and user interface.
In conclusion, the Spring Boot application, fortified with JWT Bearer authentication, furnishes robust user
management capabilities while ensuring secure access to the APIs. Its seamless integration with external services,
coupled with stringent security measures, underscores its utility and reliability in real-world scenarios.
