# What it Does
***Written in Java***. Tracks item sales, calculates transport costs, re-stocking and its effects on capital.  Also handles tracking of items with custom properties, the exporting of manifests based on current inventory, importing of manifests of newly stocked items, importing sales logs. In addition to optimizing manifests for capital expenditure by minimizing the quantity of low temperature trucks through an optimal logistical allocation of items.

# Features

 - View store capital in dollars and cents (e.g. $100,000.00). 
 - View store inventory in tabular format. Item names and quantities are displayed along with their properties: 
	 - Name. 
	 - Quantity. 
	 - Manufacturing cost (\$). 
	 - Sell price (\$). 
	 - Reorder point. 
	 - Temperature (°C).
 - Load in item properties documents. 
	 - **Effect:** Item properties are initialized.
 - Export manifests based on current inventory.
 - Load in manifests. 
	 - **Effect:** capital is decreased. 
	 - **Effect:** inventory is increased.
 - Load in sales logs. 
	 - **Effect:** capital is increased. 
	 - **Effect:** inventory is decreased
 
 

## REPO INCLUDES
>**JAVA SOURCE CODE**

 >**FULL DOCUMENTATION**

>**SAMPLE FILES TO TEST SOFTWARE**

>**UNIT TESTING**
