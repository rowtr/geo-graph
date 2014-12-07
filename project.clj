(defproject raywillig/geo-graph "0.0.5"
  :description "directed graph using google maps data"
  :url "https://github.com/rwillig/geo-graph"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm     {:name "git "
            :url  "https://github.com/rwillig/geo-graph"}          
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [raywillig/geo-cache "0.0.6"]
                 [raywillig/google-maps-web-api "0.1.4"]
                 [org.clojure/math.combinatorics "0.0.4"]])
