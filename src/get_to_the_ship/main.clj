(ns get-to-the-ship.main
  (:gen-class)
  (:require
   [clojure.core.async :as a :refer [chan go go-loop <! >! <!! >!!  take! put! offer! poll! alt! alts! close! onto-chan!
                                     pub sub unsub mult tap untap mix admix unmix pipe
                                     timeout to-chan  sliding-buffer dropping-buffer
                                     pipeline pipeline-async]]
   [clojure.string]
   [clojure.spec.alpha :as s]
   [clojure.java.io :as io])
  (:import
   (javax.swing JFrame JLabel JButton SwingConstants JMenuBar JMenu)
   (java.awt.event WindowListener)))

(println "clojure.compiler.direct-linking" (System/getProperty "clojure.compiler.direct-linking"))
(do (set! *warn-on-reflection* true) (set! *unchecked-math* true) (clojure.spec.alpha/check-asserts true))

(defonce stateA (atom nil))

(defn -main [& args]
  (println ::-main)
  (let []
    (reset! stateA {})
    (add-watch stateA :watch-fn (fn [k stateA old-state new-state] new-state))

    (let [jframe (JFrame. "get-to-the-ship")]

      (doto jframe
        (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
        (.setSize 1600 1200)
        (.setLocationByPlatform true)
        (.setVisible true)
        (.setJMenuBar (doto (JMenuBar.)
                        (.add (JMenu. "program"))))))

    (go)))

(comment

  (-main)

  (do
    (require
     '[get-to-the-ship.main]
     :reload)
    (swap! stateA assoc ::random (rand-int 1000)))

  
  ;
  )