# social-score

Java based REPL tool


# Pre-requisites

|Tool| Version  |
|--|--|
| Java | 8 |


# Running

- Run `./mvnw compile` on Linux/macOS based platforms
    - **OR** Run `mvnw.bat compile` on Windows
- To ADD a new social score (no extra spaces)
  - `add --url https://www.rte.ie/news/politics/2018/1004/1001034-cso/ --score 20`
    - Or using shorthand command
    - `add -u https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine/ -s 30`
- To EXPORT the social score report into a csv file
  - `export --out report.csv`
- To REMOVE a social score entry
  - `remove --url https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine/`
- To Quit
  - Press `Ctrl + C` or type `quit`
