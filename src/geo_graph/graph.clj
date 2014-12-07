(ns geo-graph.graph)

(defprotocol IGraph
  (weight [this weight row col])
  (distance [this row col])
  (duration [this row col])
  (dimension [this])
  (row-col->index [this row col]))

(defrecord Graph [dimension distance duration]
  IGraph
  (weight [this weight row col] (nth (weight this)(+ (* row (:dimension this)) col)))
  (distance [this row col] (weight this :distance row col))
  (duration [this row col] (weight this :duration row col))
  (dimension [this] (:dimension this))
  (row-col->index [this row col] (+ (* row (:dimension this)) col)))

(defn make-graph [dim distance duration]
  (Graph. dim distance duration))

(defrecord ConcurrentGraph [dimension vals]
  IGraph
  (weight [this weight row col] (weight (get (:vals this) [row col])))
  (distance [this row col] (weight this :distance row col))
  (duration [this row col] (weight this :duration row col))
  (dimension [this] (:dimension this))
  (row-col->index [this row col] (+ (* row (:dimension this)) col)))   

(defn make-concurrent-graph [dimension vals]
  (ConcurrentGraph. dimension vals))

(defn trip
  [g w es]
  (reduce + (mapv #(weight g w (first %) (second %))  es )))

(defn intra
  [g w es]
  (mapv #(weight g w (first %) (second %)) es))


(defn vertices->edges
  "create a tour [x x+1][x+1 x+2]... from a list of vertices"
  [vs]
  (mapv vec (partition 2 1 vs)))

(defn edges->vertices
  "turn a tour of the form ([a b] [b c] [c d]...) into a path of the form [a b c d]"
  [es]
  (mapv (fn[x] (first x))(conj (vec es) (apply vector (reverse (last es))))))
