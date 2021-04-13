(ns cljapp.views
  (:require
   [re-frame.core :as re-frame]
   [cljapp.subs :as subs]
   ))

(defn navbar []
	[:div.level.is-mobile {:style {:padding "2% 2% 2% 2%"}}
		[:div.level-left
			[:div.level-item.title.is-5 [:a "GIGION"]]]
		[:div.level-right
		    [:div.level-item.title.is-6 [:a "Sign In"]]]])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div.container
    	[:div.columns.is-centered
     		[:div.column.is-12 [navbar]]]

     [:div
	     [:div.box {:style {:margin-top "2%" :padding "0% 10% 4% 10%" :border-color "black"}}

	      [:div.columns.is-centered.is-vcentered
	        [:div.column
	            [:div.title.is-8.has-text-info {:style {:text-align "center"}}
	            (str "Welcome to the Worlds first micro-consulting platform")]]]

	       [:div.columns.is-centered.is-vcentered
	        [:div.column
	            [:div.title.is-5 {:style {:text-align "center"}}
	            (str "Your expertise can help someone, somewhere in the world and we can help you to monetize your expertise")]]]
	       
	       [:div.columns.is-centered.is-vcentered
	        [:div.column
	            [:div.title.is-6 {:style {:text-align "center" :margin-top "-2%"}}
	            (str "Please ") [:a "click here"] (str " to sign up as a provider")]]]

	        [:div.columns.is-centered.is-vcentered
	        	[:div.column {:style {:color "black"}}
	            [:div.title.is-5 {:style {:text-align "center"}}
	            (str "Are you looking at a Job at a certain company?")]]]
	       	[:div.columns.is-centered.is-vcentered
	        	[:div.column
	            [:div.title.is-6 {:style {:text-align "center" :margin-top "-2%"}}
	            (str "Please ") [:a "click here"] (str " and we can connect you with an employee at that company that can help you")]]]
	   
	        [:div.columns.is-centered.is-vcentered
	        	[:div.column
	            [:div.title.is-5 {:style {:text-align "center"}}
	            (str "Are you planning to pursue higher education at a certain college?")]]]
	       	[:div.columns.is-centered.is-vcentered
	        	[:div.column
	            [:div.title.is-6 {:style {:text-align "center" :margin-top "-2%"}}
	            (str "Please ") [:a "click here"] (str " and we can connect you with an current or former student or faculty at that college who can answer all your questions")]]]

	        [:div.columns.is-centered.is-vcentered
	        	[:div.column
	            [:div.title.is-5 {:style {:text-align "center"}}
	            (str "Are you a Recruiter?")]]]
	       	[:div.columns.is-centered.is-vcentered
	        	[:div.column
	            [:div.title.is-6 {:style {:text-align "center" :margin-top "-2%"}}
	            (str "Please ") [:a "click here"] (str " and we can connect you with employees at any company that you want to target")]]]

	        [:div.columns.is-centered.is-vcentered
	        	[:div.column
	            [:div.title.is-5 {:style {:text-align "center"}}
	            (str "Are you a Private Tutor?")]]]
	       	[:div.columns.is-centered.is-vcentered
	        	[:div.column
	            [:div.title.is-6 {:style {:text-align "center" :margin-top "-2%"}}
	            (str "Please ") [:a "click here"] (str " and we can help streamline and monetize your outside the classroom communications with all your students")]]]
	        

	        [:div.columns.is-centered.is-vcentered
	        	[:div.column
	            [:div.title.is-5 {:style {:text-align "center"}}
	            (str "Are you a Doctor?")]]]
	       	[:div.columns.is-centered.is-vcentered
	        	[:div.column
	            [:div.title.is-6 {:style {:text-align "center" :margin-top "-2%"}}
	            (str "Please ") [:a "click here"] (str " and we can help streamline and monetize your outside the clinic communications with all your patients")]]]

   ]]]))
