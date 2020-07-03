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

(defn play-round
 [answer]
 [1 2 3 4])

(defn game-loop
 ""
 ([answer] (game-loop answer []))
 ([answer rounds]
  ;(println "past rounds" rounds)
  ;(println "round" round-count)
  (if (< (count rounds) 10)
   (do
    (def r (conj rounds (play-round answer)))
    (println r)
    (game-loop answer r))
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
