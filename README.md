It's just example to show error `For request 'POST /abc' [Invalid Json: No content to map due to end-of-input at [Source: [B@31e69b8b; line: 1, column: 1]]`

To reproduce:
* Step 1
Run application
* Step 2
Send any POST request to any url with json header, for example:

POST /abc HTTP/1.1
Host: localhost:9000
Content-Type: application/json
Cache-Control: no-cache

{}
