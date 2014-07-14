(ns geo-graph.graph)

(defprotocol IGraph
  (weight [this row col])
  (dimension [this])
  (row-col->index [this row col]))

(defrecord Graph [dimension weight alt-weight]
  IGraph
  (weight [this row col] (nth (:weight this)(+ (* row (:dimension this)) col)))
  (dimension [this] (:dimension this))
  (row-col->index [this row col] (+ (* row (:dimension this)) col)))

(defn make-graph [dim w alt]
  (Graph. dim w alt))

(defrecord ConcurrentGraph [dimension vals weight alt-weight]
  IGraph
  (weight [this row col] ((:weight this) (get (:vals this) [row col])))
  (dimension [this] (:dimension this))
  (row-col->index [this row col] (+ (* row (:dimension this)) col)))   

(defn make-concurrent-graph [dimension vals weight alt-weight]
  (ConcurrentGraph. dimension vals weight alt-weight))

(defn trip
  [g es]
  (reduce + (mapv #(weight g (first %) (second %))  es )))

(defn intra
  [g es]
  (mapv #(weight g (first %) (second %)) es))


(defn vertices->edges
  "create a tour [x x+1][x+1 x+2]... from a list of vertices"
  [vs]
  (mapv vec (partition 2 1 vs)))
;  (apply map vector (list t (rest (take (inc (count t)) t)))))

(defn edges->vertices
  "turn a tour of the form ([a b] [b c] [c d]...) into a path of the form [a b c d]"
  [es]
  (mapv (fn[x] (first x))(conj (vec es) (apply vector (reverse (last es))))))
