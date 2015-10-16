(set-env!
  :resource-paths #{"src"}
  :dependencies '[[raywillig/geo-cache                  "0.0.7"]
                  [rowtr/google-maps-web-api            "0.1.6"]
                  [org.clojure/math.combinatorics       "0.0.4"]])


(task-options!
  pom {:project 'rowtr/geo-graph
       :version "0.0.8"
       :description "geo graph library" })

