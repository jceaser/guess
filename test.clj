(defn zipm [keys vals]
    (loop [m {}
          [k & ks :as keys] (seq keys)
          [v & vs :as vals] (seq vals)]
      (if
        (and ks vs)
        (recur (assoc m k v) ks vs) m)))
(zipm [:a :b :c] [1 2 3])

(defn zipm2 [keys vals] (reduce (fn [m [k v]] (assoc m k v)) {} (map vector keys vals)))

(zipm2 [:a :b :c] [1 2 3])

(map vector [:a :b :c] [1 2 3])


