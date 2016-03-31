(ns todo.views.layout
  (:use [hiccup.page :only (html5 include-css include-js)])
  )

(defn application [title & content]
  (html5 {:lang "en"}
    [:head
        [:title title]
      [:body
        [:div {:class "container"} 
          content]
        ]
      ]
    )
  )