(ns guess.core
  (:gen-class))

(defn build-answer
 "Recursivly Create a vector of answers
 answer is a vector which initially should be empty
 add-count is the number of random items to add to answer
 "
 ([] (build-answer 4))
 ([add-count] (build-answer add-count []))
 ([add-count answer]
 (if (< 0 add-count)
  (build-answer (dec add-count) (conj answer (rand-int 3)))
  answer)))

(defn true-count
  "counts the number of true values exist in the given list"
  [list]
  (get (frequencies list) true))

(defn positions-match
  "find how many positions match and return true if all match"
  [answer round]
  ;(def a (reduce #(or %1 (some? (some #{%2} answer))) false round))
  ;(print "stub: check" round "for positions matching:")
  (loop [x 0
         v [] ]
    (if (< x 4)
      (recur (inc x) (conj v (= (get round x) (get answer x))))
      (println "Exact Matches:" (true-count v)))))

(defn contain-match
  "util function to check a value in a list"
  [list value]
  (some #(= value %) list))

(defn contain-matches
  "checks the round against the answer and looks for any items that are correct"
  [answer round]
  (let [exact (true-count (map #(contain-match answer %) round))]
    (println "Correct Items:" exact))
  true
  )

(defn positions-match2
 "report how many positions are the same"
 ([answer round] (contain-matches answer round 0))
 ([answer round index]
 (println "stub: check" round "for matches in" answer "with" (nth answer index) "and" (nth round index) "using" index "which is below" (count answer))
 (if (< index (-(count answer) 1))
   (contain-matches answer round (inc index)))
 (if (= (nth answer index) (nth round index)) (println " found") :nop )))

(defn play-round
 "Play one round, ask for input, then judge it against the answer
 answer is a vector of numbers
 return is a vector of numbers, the guess
 "
 [answer]
 [1 2 3 4])

(defn judge-rounds
 "judge the last round and report, return true if game can continue"
 [answer rounds]
 (let [round (last rounds)]
  (and (contain-matches answer round) (positions-match answer round))))

(defn game-loop
 ""
 ([answer] (game-loop answer []))
 ([answer rounds]
  ;(println "answer is:" answer)
  ;(println "past rounds" rounds)
  ;(println "round" round-count)
  (if (< (count rounds) 10)
   (do
    (if (judge-rounds answer (conj rounds (play-round answer)))
     (game-loop answer rounds)))
   rounds))
)

(defn -main
 "guessing game"
 [& args]
 (println "Guessing game")
 (game-loop (build-answer))
 (println "Done")
 ""
)
