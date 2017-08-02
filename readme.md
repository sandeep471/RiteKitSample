I tested this app on Android v4.4.2 (Samsung Galaxy S4)

I solved this by breaking it into 2 parts. First part is to authenticate using social networks and the second part is retrieving data from RiteKit API. 
Authentication is developed by following the steps explained in the Facebook and Twitter documentation.
Trending Hashtags and Influencers are pulled in JOSN format using URLs provided in RiteKit API. Then using JSON, the appropriate data is shown on the corresponding pages.

![Login](/uploads/5dc78976bdcf63111d56a2fb3baf2829/Login.png)

![Trending](/uploads/10c76647d41263b7de8ae33b5186e669/Trending.png)

![Influencers](/uploads/949d80309af5c27a64af795124a520bc/Influencers.png)