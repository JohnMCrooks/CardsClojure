(ns cards-clojure.core
  (:gen-class))

(def suits [:clubs :spades :hearts :diamonds])

(def ranks (range 1 14))

(defn create-deck []    ; creates a deck of 52 cards
  (set                  ;set will take an existing collection and make it a hash-set 
    (for [suit suits    ;this is how you create a for loop in clojure. 
          rank ranks]   ;this runs the loops concurrently 
      {:suit suit :rank rank})))           ; this line is the return value of the for loop and will return a hash-set

(defn create-hands [deck]   ; returns hash-set of all possible hands
  (set
    (for [c1 deck
          c2 (disj deck c1)
          c3 (disj deck c1 c2)
          c4 (disj deck c1 c2 c3)]
      #{c1 c2 c3 c4})))       ;setting the hand as a hash-set, for loops return as lists though so we also need to add the set at the top
    
(defn flush? [hand]              ; returns a boolean statign whether or not a particular hand is a flush
  (let [suits (set (map :suit hand))]
    (= 1 (count suits))))

(defn -main []
  (let [deck (create-deck)
        deck (conj deck {:suit :spades :rank 1})  ;adding an already existing hand to the deck to make sure it's handling duplicates correctly
        hands (create-hands deck)
        flushes (filter flush? hands)]
    (count flushes)))
   
