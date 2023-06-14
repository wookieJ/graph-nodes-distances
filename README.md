# Nodes distance to root

## How to get list of nodes and their distances to root?
Simply run app and use REST API to return list of nodes.

API consumes graph and starting node in request (in JSON format) in following structure:

```yaml
request:
    graph: Map<String, List<Edge>>
    startNode: String

Edge:
    id: String
    type: String ("BLUE" | "RED")
```

And then call API. There is an endpoint `POST /graphs/nodes`. Example curl:
```shell
curl -s -X POST 'http://localhost:8080/graphs/nodes' \
-H 'Content-Type: application/json' \
--data-raw '{
    "graph": {
        "A": [
            {
                "id": "B",
                "type": "RED"
            },
            {
                "id": "C",
                "type": "BLUE"
            }
        ],
        "B": [
            {
                "id": "A",
                "type": "RED"
            }
        ],
        "C": [
            {
                "id": "A",
                "type": "BLUE"
            }
        ]
    },
    "startNode": "A"
}'
```

## Hot to test?
To test simply run in project's path: 
```shell
./gradlew test
```
