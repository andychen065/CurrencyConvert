1.Key points of development
a.Showing usage when run programe without argument inputted or the first argument is "--help".
b.4 optional argments are provided: --field, --multiplier， -i， -o。
c.The "--field" argument is mandatory, other 3 arguments are used default value 1, data.csv and 
  data-fr.csv if they are not inputted.
d.Format the currency amount base on the local currency format.
e.Only use JAVA IO to paser CSV.
f.Quotes the output value if it includes comma.
g.Support two types argument foramt. One type is with double hyphen (e.g.--field and --multiplier) 
  which must has argument value after it, another type is with single hyphen (e.g. -i and -o) which 
  input value directly.


2.Introduce
This programe is used for converting prince from USD to EUR. Input and output data in batch once 
specify the column of the source price and convert rate to destination price.
It is developed under jdk7, if it shows "Unsupport version exception", Please try re-complie by source.
We provide two script folder, named currency_convert.bat and currency_convert.sh，Pleaes change the JAVA HOME path.


3.Run method
a.Unzip to any foler.
b.If run in Windows, please open dos window and CD into the unzip foler. Command line:

	rem entry the unzip foler
	cd /d <target folder> 

	rem run the batch script
	currency_convert.bat --field 2 --multiplier 0.6 data.csv data-fr.csv
	currency_convert.bat --field 2 --multiplier 0.6 stdin stdout

	Or
	rem run java command with the class directly
	java -Xmx256M -Xms256M -classpath ./classes com.andy.practice.CurrencyConvert --field 2 --multiplier 0.6 data.csv data-fr.csv

c.If run in Linux, please open terminal and CD into the unzip foler. Command line:

	# entry the unzip foler
	cd /d <target folder>

	# run the shell script
	currency_convert.sh --field 2 --multiplier 0.6 data.csv data-fr.csv
	currency_convert.sh --field 2 --multiplier 0.6 stdin stdout
	
	Or
	# run java command with the class directly
	java -Xmx256M -Xms256M -classpath ./classes com.andy.practice.CurrencyConvert --field 2 --multiplier 0.6 data.csv data-fr.csv

4.Argument Usage
a.Usage1: currency_convert.bat --field 2, other 3 arguments are used default value 1, data.csv and data-fr.csv.
b.Usage2: currency_convert.bat --field 2 --multiplier 0.6, other 2 arguments are used default value data.csv and data-fr.csv.
c.Usage3: currency_convert.bat --field 2 --multiplier 0.6 stdin data-fr.csv, that means accept standard input and output with file.
d.Usage4: currency_convert.bat --field 2 --multiplier 0.6 data.csv stdout, that means accept file input and standard output.