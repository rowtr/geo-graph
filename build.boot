(set-env!
  :resource-paths #{"src"}
  :dependencies '[[rowtr/geo-cache                      "0.0.11"]
                  [rowtr/google-maps-web-api            "0.1.8"]
                  [org.clojure/tools.logging            "0.3.1"]
                  [org.clojure/math.combinatorics       "0.0.4"]])


(task-options!
  push {:repo "clojars-upload"}
  pom {:project 'rowtr/geo-graph
       :version "0.0.12"
       :description "geo graph library" })

