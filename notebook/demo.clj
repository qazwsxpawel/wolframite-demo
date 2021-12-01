(ns notebook.demo
  (:require [clojuratica.core  :refer [load-all-symbols wl] :as w]
            [clojuratica.base.convert :as cv]
            [clojuratica.jlink :as jlink]
            [clojuratica.lib.helpers :refer [help!]]
            [clojuratica.tools.clerk-helper :refer [view]]
            [nextjournal.clerk :as nb]
            [nextjournal.beholder :as beholder]
            [nextjournal.clerk.webserver :as webserver]
            [nextjournal.clerk.viewer :as v]
            [cemerick.pomegranate :as pom]
            [clojure.string :as str]
            [clojure.java.browse :refer [browse-url]]
            [clojure.repl :refer [doc find-doc apropos]]))


;; # Geo

(view '(GeoGraphics
        [Red (GeoPath [(Entity "City" ["Portland" "Oregon" "UnitedStates"])
                       (Entity "City" ["Orlando" "Florida" "UnitedStates"])
                       (Entity "City" ["Boston" "Massachusetts" "UnitedStates"])]
                      "Geodesic")]))


;; # Time

(view '(TimelinePlot
        [(Entity "HistoricalEvent" "WorldWar1")
         (Entity "HistoricalEvent" "WorldWar2")
         (Entity "HistoricalEvent" "VietnamWar")
         (Entity "HistoricalEvent" "KoreanWarBegins")]))

(view '(GeoGraphics))

(view '(GeoImage (Entity "City" ["NewYork" "NewYork" "UnitedStates"])))


;; # Numbers

(view '(BarChart (EntityValue (EntityClass "Planet" All) "Radius")))

;; # 3D

(view '(MoleculePlot3D (Molecule "O=C(C1CCC1)S[C@@H]1CCC1(C)C")))

;; # Animate!

(view '(Animate (Plot (Sin (+ x a)) [x 0 10]) [a 0 5] (-> AnimationRunning true)))


(comment ;; Start A Clerk Notebook

    (do
      (webserver/start! {:port 7777})

      (future
        (let [watch-paths ["notebook/"]]
          (reset! nb/!watcher {:paths watch-paths
                               :watcher (apply beholder/watch-blocking #(nb/file-event %) watch-paths)})))
      (prn "Clerk Started!")
      (browse-url "http://localhost:7777"))
  
  )