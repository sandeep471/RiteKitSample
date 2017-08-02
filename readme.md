I tested this app on Android v4.4.2 (Samsung Galaxy S4)

I solved this by breaking it into 2 parts. First part is to authenticate using social networks and the second part is retrieving data from RiteKit API. 
Authentication is developed by following the steps explained in the Facebook and Twitter documentation.
Trending Hashtags and Influencers are pulled in JOSN format using URLs provided in RiteKit API. Then using JSON, the appropriate data is shown on the corresponding pages.