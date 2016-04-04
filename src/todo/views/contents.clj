(ns todo.views.contents
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
    )
  (:require 
    [todo.db :as postgres]
    [hiccup.page :refer [html5]]
    [hiccup.core :refer [html h]]
    )
  )

(defn add-todo-form [] 
  [:div
    [:form {:action "/post" :method "post"}
      [:div {:class "task"}
        [:input {:id "task-input" :type "text" :name "task-input" :placeholder "Task"}]
        ]
      [:button {:class "submit-button" :type "submit"} "Add"]
      ]
    ]
  )

(defn index [& body]
  [:div {:id "content"}
    [:h1 {:class "text-success"} "Todo List:"]
    [:ul 
      (for [x postgres/select-todo-items]
        (let [row (into (sorted-map) x)]
          (println row)
          ;body]
          [:li [:strong (str "Task: ")] (get row :task) [:strong " Done: "] (get row :done)]
          )
        )]
      (add-todo-form)
    ]
  )



(defn item-list [item]
  (println item)
  (html
    [:li [:strong (str "Task: ")] (h (:task item)) [:strong " Done: "] (h (:done item))]
    )
  )

(defn about []
  [:div {:id "content"}
    [:h1 {:class "about-header"} "About"]
    [:h3 {:class "about-text"} "This project is a to do list web app. The purpose is: "]
    [:ul {:class "about-list"}
      [:li "To gauge how well I know Clojure & some common libraries."]
      [:li "If I don't know Clojure, to gauge how well I can pick it up."]
      ]
    ]
  )

(defn not-found []
  [:div 
    [:h1 {:class "info-warning"} "Page Not Found!"]
    [:p "You navigated here somehow, but I suggest you head back! There's nothing to see here."]
    (link-to {:class "btn"} "/" "Home")
    ]
  )

