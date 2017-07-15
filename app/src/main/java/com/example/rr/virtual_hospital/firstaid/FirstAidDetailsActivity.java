package com.example.rr.virtual_hospital.firstaid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rr.virtual_hospital.R;

public class FirstAidDetailsActivity extends AppCompatActivity {
    ImageView place_img;
    TextView dis_name, dis_desc, dis_sym, what_to_do,homeRemidis,doctorsgoing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_ai_det);
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("First Aid Details List");


        place_img = (ImageView) findViewById(R.id.place_img);
        dis_name = (TextView) findViewById(R.id.dsesName);
        dis_desc = (TextView) findViewById(R.id.desesDesptn);
        dis_sym = (TextView) findViewById(R.id.symtms);
        what_to_do = (TextView) findViewById(R.id.whtToDO);
        homeRemidis=(TextView)findViewById(R.id.homeRemdyDesptn);
        doctorsgoing=(TextView)findViewById(R.id.whenToSeeaDocDesptn);

        String getDocName = getIntent().getExtras().getString("docName");

        if (getDocName.equals("Abdominal pain")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("Abdominal pain is pain that occurs between the chest and pelvic regions. Abdominal pain can be crampy, achy, dull, intermittent or sharp. It’s also called a stomachache.\n" +
                    "\n" +
                    "Inflammation or diseases that affect the organs in the abdomen can cause abdominal pain. Major organs located in the abdomen include:\n" +
                    "\n" +
                    "Intestines (small and large)\n" +
                    "kidneys\n" +
                    "appendix (a part of the large intestine)\n" +
                    "spleen\n" +
                    "stomach\n" +
                    "gallbladder\n" +
                    "liver\n" +
                    "pancreas\n" +
                    "Viral, bacterial, or parasitic infections that affect the stomach and intestines may also cause significant abdominal pain.");
            dis_sym.setText("    Belching\n" +
                    "Gas (flatus, farting)\n" +
                    "Indigestion\n" +
                    "Discomfort in the upper left or right; middle; or lower left or right abdomen\n" +
                    "Constipation\n" +
                    "Diarrhea\n" +
                    "GERD (gastro-esophageal reflux disease)\n" +
                    "Heartburn");
            what_to_do.setText("For gas pain, medicine that has the ingredient simethicone (Mylanta, Gas-X) can help get rid of it.\n" +
                    "For heartburn from gastroesophageal reflux disease (GERD), try an antacid or acid reducer.\n" +
                    "For constipation, a mild stool softener or laxative may help get things moving again.\n" +
                    "For cramping from diarrhea, medicines that have loperamide (Imodium) or bismuth subsalicylate (Kaopectate or Pepto-Bismol) might make you feel better.\n" +
                    "For other types of pain, acetaminophen (Aspirin Free Anacin, Liquiprin, Panadol, Tylenol) might be helpful. But stay away from non-steroidal anti-inflammatories (NSAIDs) like aspirin, ibuprofen (Advil, Midol, Motrin), or naproxen (Naprosyn, Aleve, Anaprox, Naprelan). They can irritate your stomach.\n");
            homeRemidis.setText("Be sure to drink plenty of clear fluids so your body has enough water.You also can do things to make stomach pain less likely:\n" +
                    "Eat several smaller meals instead of three big ones\n" +
                    "Chew your food slowly and well\n" +
                    "Stay away from foods that bother you (spicy or fried foods, for example)\n" +
                    "Ease stress with exercise, meditation, or yoga)");
            doctorsgoing.setText("When to See a Doctor\n" +
                    "It's time to get medical help if:\n" +
                    "You have severe belly pain or the pain lasts several days\n" +
                    "You have nausea and fever and can't keep food down for several days\n" +
                    "You have bloody stools\n" +
                    "It hurts to pee\n" +
                    "You have blood in your urine\n" +
                    "You cannot pass stools, especially if you're also vomiting.\n");

        }


        if (getDocName.equals("Animal Bites")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("An animal bite is a wound, usually a puncture or laceration, caused by the teeth. An animal bite usually results in a break in the skin but also includes contusions from the excessive pressure on body tissue from the bite. The contusions can occur without a break in the skin. Bites can be provoked or unprovoked. Other bite attacks may be apparently unprovoked. Biting is a physical action not only describing an attack but it is a normal response in an animal as it eats, carries objects, softens and prepares food for its young, removes ectoparasites from its body surface, removes plant seeds attached to its fur or hair, scratching itself, and grooming other animals. Animal bites often result in serious infections and mortality. Animal bites not only include injuries from the teeth of reptiles, mammals, but fish, and amphibians. Arthropods can also bite and leave injuries.");
            dis_sym.setText("Symptoms that may indicate infection include:\n" +
                    "\n" +
                    "swelling, redness, or pain that lasts more than 24 hours\n" +
                    "fluid (pus) that drains from the bite or wound\n" +
                    "red streaks that run up the hand and arm\n" +
                    "tenderness or pain under the elbow or armpit due to swollen lymph nodes\n" +
                    "loss of mobility in the finger or hand\n" +
                    "fever or chills\n" +
                    "loss of sensation in the fingertip\n" +
                    "fatigue");
            what_to_do.setText(" Stop Bleeding\n" +
                    "Apply direct pressure until bleeding stops.\n" +
                    " Clean and Protect\n" +
                    "For a wound or superficial scratch from an animal bite:\n" +
                    "Gently clean with soap and warm water. Rinse for several minutes after cleaning.\n");
            homeRemidis.setText("Apply antibiotic cream to reduce risk of infection, and cover with a sterile bandage.\");\n");
            doctorsgoing.setText("Get medical help immediately for any animal bite that is more than a superficial scratch or if the animal was a wild animal or stray, regardless of the severity of the injury");

        }




        if (getDocName.equals("Burn")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText(" There are 3 levels of burns:\n" +

                    "First-degree burns affect only the outer layer of the skin. They cause pain, redness, and swelling.\n" +
                            "Second-degree burns affect both the outer and underlying layer of skin. They cause pain, redness, swelling, and blistering. They are also called partial thickness burns.\n" +
                            "Third-degree burns affect the deep layers of skin. They are also called full thickness burns. They cause white or blackened, burned skin. The skin may be numb.\n"+
                            "Burns fall into 2 groups\n"+

                           "Minor burns are:\n"+

            "First degree burns anywhere on the body\n"+
            "Second degree burns less than 2 to 3 inches (5 to 7.5 centimeters) wide\n "+
            "Major burns include:\n"+

           " Third-degree burns+\n"+
           "Second-degree burns more than 2 to 3 inches (5 to 7.5 centimeters) wide \n"+
            "Second-degree burns on the hands, feet, face, groin, buttocks, or over a major joint\n"+
            "You can have more than 1 type of burn at a time.");
            dis_sym.setText("Burn symptoms can include:\n" +
                    "\n" +
                    "Blisters that are either intact (unbroken) or have ruptured and are leaking fluid\n" +
                    "Pain (How much pain you have is unrelated to the level of burn. The most serious burns can be painless.)\n" +
                    "Peeling skin\n" +
                    "Shock (Watch for pale and clammy skin, weakness, blue lips and fingernails, and a drop in alertness.)\n" +
                    "Swelling\n" +
                    "Red, white, or charred skin\n" +
                    "If you have burned your airways, you may have:\n" +
                    "\n" +
                    "Burns on the head, face, neck, eyebrows, or nose hairs\n" +
                    "Burned lips and mouth\n" +
                    "Coughing\n" +
                    "Difficulty breathing\n" +
                    "Dark, black-stained mucus\n" +
                    "Voice changes\n" +
                    "Wheezing");
            what_to_do.setText("MINOR BURNS\n" +
                    "\n" +
                    "If the skin is unbroken:\n" +
                    "\n" +
                    "Run cool water over the area of the burn or soak it in a cool water bath (not ice water). Keep the area under water for at least 5 minutes. A clean, cold, wet towel will help reduce pain.\n" +
                    "Calm and reassure the person.\n" +
                    "After flushing or soaking the burn, cover it with a dry, sterile bandage or clean dressing.\n" +
                    "Protect the burn from pressure and friction.\n" +
                    "Over-the-counter ibuprofen or acetaminophen can help relieve pain and swelling. Do NOT give aspirin to children under 12.\n" +
                    "Once the skin has cooled, moisturizing lotion also can help.\n" +
                    "Minor burns will often heal without further treatment. Make sure the person is up to date on their tetanus immunization.\n" +
                    "\n" +
                    "MAJOR BURNS\n" +
                    "\n" +
                    "If someone is on fire, tell the person to stop, drop, and roll. Then, follow these steps:\n" +
                    "\n" +
                    "Wrap the person in thick material; such as a wool or cotton coat, rug, or blanket. This helps put out the flames.\n" +
                    "Pour water on the person.\n" +
                    "Call 911 or your local emergency number.\n" +
                    "Make sure that the person is no longer touching any burning or smoking materials.\n" +
                    "Do NOT remove burned clothing that is stuck to the skin.\n" +
                    "Make sure the person is breathing. If necessary, begin rescue breathing and CPR.\n" +
                    "Cover the burn area with a dry sterile bandage (if available) or clean cloth. A sheet will do if the burned area is large. Do NOT apply any ointments. Avoid breaking burn blisters.\n" +
                    "If fingers or toes have been burned, separate them with dry, sterile, non-sticky bandage.\n" +
                    "Raise the body part that is burned above the level of the heart.\n" +
                    "Protect the burn area from pressure and friction.\n" +
                    "If an electrical injury may have caused the burn, DO NOT touch the victim directly. Use a non-metallic object to move the person away from exposed wires before starting first aid.");
            homeRemidis.setText("The first responsibility in the care of the burn patient is to assess the patency of the airway and to ensure that breathing is unimpaired. The immediate care of the burn itself involves the removal of any overlying clothing and jewelry and the irrigation of the affected tissues with cool water, taking care to avoid excessively cooling the body. To help prevent hypothermia and infection, cover the burn wounds with sterile dressings if available, or a clean sheet, separating burn wound surfaces");
            doctorsgoing.setText("Patients with large or complex burn injuries should be transferred to regional burn centers or to the care of surgeons with special interest in burn management.\n" +
                    "\n");
        }

        if (getDocName.equals("Choking ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);

            dis_desc.setText(" Choking is the mechanical obstruction of the flow of air from the environment into the lungs. Choking prevents breathing, and can be partial or complete, with partial choking allowing some, although inadequate, flow of air into the lungs. ... Physical obstruction of the airway by a foreign body.");
            dis_sym.setText("Bluish skin color.\n" +
                    "Difficulty breathing - ribs and chest pull inward.\n" +
                    "Loss of consciousness if blockage is not cleared.\n" +
                    "Inability to cry or make much sound.\n" +
                    "Weak, ineffective coughing.\n" +
                    "Soft or high-pitched sounds while inhaling.");

            homeRemidis.setText("If the Person Is Conscious but Not Able to Breathe or Talk:\n" +
                    "Give up to 5 blows between the shoulder blades with the heel of your hand.\n" +
                    "If Person Is Still Choking, Do Thrusts\n" +
                    "If the person is not pregnant or too obese, do abdominal thrusts:\n" +
                    "Stand behind the person and wrap your arms around the waist.\n" +
                    "Place your clenched fist just above the person’s navel. Grab your fist with your other hand.\n" +
                    "Quickly pull inward and upward as if trying to lift the person up.\n" +
                    "Perform a total of 5 abdominal thrusts.\n" +
                    "If the blockage is still not dislodged, continue cycles of 5 back blows and 5 abdominal thrusts until the object is coughed up or the person starts to breathe or cough.\n" +
                    "Take the object out of his mouth only if you can see it. Never do a finger sweep unless you can see the object in the person's mouth.\n" +
                    "If the person is obese or pregnant, do high abdominal thrusts:\n" +
                    "Stand behind the person, wrap your arms them, and position your hands at the base of the breast bone.\n" +
                    "Quickly pull inward and upward.\n" +
                    "Repeat until the object is dislodged.\n");
            doctorsgoing.setText("Seek medical help as soon as possible.");
        }

        if (getDocName.equals("Cuts or wounds")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("A wound is a type of injury which happens relatively quickly in which skin is torn, cut, or punctured (an open wound), or where blunt force trauma causes a contusion (a closed wound). In pathology, it specifically refers to a sharp injury which damages the dermis of the skin.");


            homeRemidis.setText("Take the following steps for minor cuts.\n" +
                    " Stop the Bleeding\n" +
                    "Apply direct pressure on the area.\n" +
                    " Clean and Protect\n" +
                    "Clean the area with warm water and gentle soap.\n" +
                    "Apply an antibiotic ointment to reduce chance of infection.\n" +
                    "Put a sterile bandage on the area. In some people, antibiotic ointments may cause a rash. If this happens, stop using the ointment.\n");
            doctorsgoing.setText("Seek medical help if:\n" +
                    "The cut is deep or over a joint.\n");
        }


        if (getDocName.equals("Deafness")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("Hearing loss refers to a diminished ability to hear sounds like other people do, while deafness refers to the inability to understand speech through hearing even when sound is amplified. Profound deafness means the person cannot hear anything at all; they are unable to detect sound, even at the highest volume possible.");
            dis_sym.setText("Signs and symptoms of hearing loss may include:\n" +
                    "\n" +
                    "    Muffling of speech and other sounds\n" +
                    "    Difficulty understanding words, especially against background noise or in a crowd of people\n" +
                    "    Trouble hearing consonants\n" +
                    "    Frequently asking others to speak more slowly, clearly and loudly\n" +
                    "    Needing to turn up the volume of the television or radio\n" +
                    "    Withdrawal from conversations\n" +
                    "    Avoidance of some social settings\n");
            what_to_do.setText("Types of conductive hearing loss include congenital absence of ear canal or failure of the ear canal to be open at birth, congenital absence, malformation, or dysfunction of the middle ear structures, all of which may possibly be surgically corrected. If these are not amenable to successful surgical correction, then the hearing alternatively may be improved with amplification with a bone conduction hearing aid, or a surgically implanted, osseointegrated device (for example, the Baha or Ponto System), or a conventional hearing aid, depending on the status of the hearing nerve.");
            homeRemidis.setText("Foods that contain beta-carotene and antioxidants are very effective in treating hearing loss and deafness.\n"+
            "To get the best results, you can consider making a bone marrow soup with some calve bones or sheep bones. You can add 3 pounds of bones and 1/3 cup each of black beans, kidney beans, and adzuki beans, 2 diced carrots, 2 diced celery stalks, 1/2 cup of dried seaweed and 1 sliced onion to about 8 cups of water in a pot. Add 1 tsp. each of turmeric, cumin and black pepper to this season. For the best results, do not use salt.\n"+
            "\n"+
            "\n"+
            "\n");
        }

        if (getDocName.equals("Dehydration")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("Dehydration occurs when you use or lose more fluid than you take in, and your body doesn't have enough water and other fluids to carry out its normal functions. If you don't replace lost fluids, you will get dehydrated.");
            dis_sym.setText("    Increased thirst.\n" +
                    "    Dry mouth.\n" +
                    "    Tired or sleepy.\n" +
                    "    Decreased urine output.\n" +
                    "    Urine is low volume and more yellowish than normal.\n" +
                    "    Headache.\n" +
                    "    Dry skin.\n" +
                    "    Dizziness.");
            what_to_do.setText("1. Replace Fluids. For mild dehydration or while waiting for medical care for an adult with severe dehydration that is not due to heat stroke: The person should try to drink 2 quarts of fluid, such as water, juice, or sports drinks (clear fluids, best), in 2 to 4 hours.");
            homeRemidis.setText("Drink a rehydration drink, water, juice, or sports drink to replace fluids and minerals. Drink 2 qt (2 L) of cool liquids over the next 2 to 4 hours. You should drink at least 10 glasses of liquid a day to replace lost fluids. You can make an inexpensive rehydration drink at home.");
            doctorsgoing.setText("Seek the doctors help according to the condition of the health");


        }


        if (getDocName.equals("Diarrhea")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("Diarrhea, also spelled diarrhoea, is the condition of having at least three loose or liquid bowel movements each day. It often lasts for a few days and can result in dehydration due to fluid loss. Signs of dehydration often begin with loss of the normal stretchiness of the skin and irritable behaviour.");
            dis_sym.setText(" Diarrhea occurs because more fluid passes through the large intestine (colon) than that organ can absorb. As a rule, the colon can absorb several times more fluid than is required on a daily basis. However, when this reserve capacity is overwhelmed, diarrhea occurs.\n" +
                    "\n" +
                    "Diarrhea is caused by infections or illnesses that either lead to excess production of fluids or prevent absorption of fluids. Also, certain substances in the colon, such as fats and bile acids, can interfere with water absorption and cause diarrhea. Rapid passage of material through the colon can also do the same.\n" +
                    "\n" +
                    "Symptoms related to any diarrheal illness are often those associated with any injury to the gastrointestinal tract, such as fever , nausea , vomiting , and abdominal pain . All or none of these may be present depending on the disease causing the diarrhea. The number of bowel movements can vary—up to 20 or more per day. In some persons, blood or pus is present in the stool. Bowel movements may be difficult to flush (float) or contain undigested food material.\n" +
                    "\n" +
                    "The most common causes of acute diarrhea are infections (the cause of traveler's diarrhea), food poisoning , and medications. Medications are a frequent and often over-looked cause, especially antibiotics and antacids. Less often, various sugar-free foods, which sometimes contain poorly absorbable materials, cause diarrhea.\n" +
                    "\n" +
                    "Chronic diarrhea is frequently due to many of the same things that cause the shorter episodes (infections, medications, etc.); however, symptoms last longer. Some infections can become chronic. This occurs mainly with parasitic infections (such as Giardia ) or when people have altered immunity (such as AIDS ). In children, more common causes of chronic diarrhea are food allergy and lactose intolerance . Toddlers who drink too much juice can have frequent, loose stools.\n" +
                    "\n" +
                    "Read more: http://www.healthofchildren.com/D/Diarrhea.html#ixzz4enFplItd\n");
            what_to_do.setText("\n" +
                    "    Take frequent, small sips of water or a rehydration drink and small bites of salty crackers.\n" +
                    "        Try to increase your fluid intake to at least 1 qt (1 L) per hour for 1 to 2 hours, or longer if you keep having large amounts of diarrhea. Note: If you have kidney, heart, or liver disease and have to limit fluids, talk with your doctor before you increase the amount of fluids you drink. \n" +
                    "    Begin eating mild foods the next day or sooner, depending on how you feel.\n" +
                    "        Avoid spicy foods, fruits, alcohol, and caffeine until 48 hours after all symptoms have disappeared.\n" +
                    "        Avoid chewing gum that contains sorbitol.\n" +
                    "        Avoid milk for 3 days after symptoms disappear. You can eat cheese or yogurt with probiotics.\n");

        }

        if (getDocName.equals("Ear Infection")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("An ear infection (acute otitis media) is most often a bacterial or viral infection that affects the middle ear, the air-filled space behind the eardrum that contains the tiny vibrating bones of the ear. ... Ear infections frequently are painful because of inflammation and buildup of fluids in the middle ear.");
            dis_sym.setText("The onset of signs and symptoms of ear infection is usually rapid.\n" +
                    "Children\n" +
                    "\n" +
                    "Signs and symptoms common in children include:\n" +
                    "\n" +
                    "    Ear pain, especially when lying down\n" +
                    "    Tugging or pulling at an ear\n" +
                    "    Difficulty sleeping\n" +
                    "    Crying more than usual\n" +
                    "    Acting more irritable than usual\n" +
                    "    Difficulty hearing or responding to sounds\n" +
                    "    Loss of balance\n" +
                    "    Fever of 100 F (38 C) or higher\n" +
                    "    Drainage of fluid from the ear\n" +
                    "    Headache\n" +
                    "    Loss of appetite\n" +
                    "\n" +
                    "Adults\n" +
                    "\n" +
                    "Common signs and symptoms in adults include:\n" +
                    "\n" +
                    "    Ear pain\n" +
                    "    Drainage of fluid from the ear\n" +
                    "    Diminished hearing\n");
            what_to_do.setText("Use pain relievers. Pain relievers such as nonsteroidal anti-inflammatory medicines (Advil, Aleve, and Motrin, for example) and acetaminophen (such as Tylenol) will help your child feel better. ...\n" +
                    "    Apply heat to the ear, which may help with pain. Use a warm washcloth.\n" +
                    "    Encourage rest. ...\n" +
                    "    Use eardrops.");
            homeRemidis.setText("Salt. Salt is probably the most readily available home remedy. ...\n" +
                    "Garlic. Garlic has antimicrobial properties and natural pain relieving qualities, making it highly effective in the treatment of ear infections. ...\n" +
                    "Basil. ...\n" +
                    "Apple Cider Vinegar. ...\n" +
                    "Olive Oil. ...\n" +
                    "Warm Water Bottle.");
            doctorsgoing.setText("Seek Medicle Help As Early As Possible");

        }
        if (getDocName.equals("Elbow Pain")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("Tennis elbow is a condition caused by inflammation of the tendons on the outer bony prominence (lateral epicondyle) of the elbow. Certain repetitive movements of the wrist can cause this condition. Tennis elbow can occur in anyone who strains the tendons of the forearm and is not limited to tennis players");
            dis_sym.setText("Elbow pain has many other causes including trauma, arthritis, and bursitis. Funny bone sensation is irritation of a nerve at the elbow that causes numbness and tingling of the inner elbow, forearm as well as little and ring fingers. Bacteria can infect the skin of a scraped (abraded) elbow to cause pain");
            what_to_do.setText("    Step 1: Identify the location of your elbow pain. ...\n" +
                    "    Step 2: Identify the cause of your elbow pain. ...\n" +
                    "    Step 3: Begin treatment. ...\n" +
                    "    Heat. ...\n" +
                    "    Stretch. ...\n" +
                    "    Counterforce brace. ...\n" +
                    "    Wrist immobilization brace.");
            homeRemidis.setText("1. Ice\n" +
                    "\n" +
                    "Icing the elbow is one of the simplest and easiest ways to reduce pain and swelling. Ice may also help to prevent progression of the condition. Instead of ice, you can also use a frozen pea bag\n." +
                    "2. Contrast Hydrotherapy\n" +
                    "\n" +
                    "Contrast hydrotherapy, which means alternating hot and cold compresses on the affected area, is another good remedy for tennis elbow. While the heat will increase blood circulation and reduce pain, the cold will reduce inflammation as well as swelling.urmeric contains curcumin, which works as an anti-inflammatory agent as well as a natural painkiller. Furthermore, turmeric is rich in antioxidants that eliminate free radicals and speeds up healing time.\n" +
                    "\n" +
                    "Mix one teaspoon of turmeric powder in one cup of milk. Heat it over low heat. Add a little honey and then drink this twice a day, at least for a few weeks.\n" +
                    "Another option is to take 250 to 500 milligram turmeric capsules three times daily, but consult with your doctor first");
            doctorsgoing.setText("An obvious deformity in your elbow\n" +
                    "A protruding bone");
        }


        if (getDocName.equals("Heat Stroke ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("Fecal incontinence is the inability to control bowel movements, causing stool (feces) to leak unexpectedly from the rectum. ... Common causes of fecal incontinence include diarrhea, constipation, and muscle or nerve damage");
            dis_sym.setText("Causes can include:\n" +
                    "\n" +
                    "    Muscle damage. Injury to the rings of muscle at the end of the rectum (anal sphincter) may make it difficult to hold stool back properly. ...\n" +
                    "    Nerve damage. ...\n" +
                    "    Constipation. ...\n" +
                    "    Diarrhea. ...\n" +
                    "    Loss of storage capacity in the rectum. ...\n" +
                    "    Surgery. ...\n" +
                    "    Rectal prolapse. ...\n" +
                    "    Rectocele.");
            what_to_do.setText("Medications. Depending on the cause of fecal incontinence, options include: Anti-diarrheal drugs such as loperamide hydrochloride (Imodium) and diphenoxylate and atropine sulfate (Lomotil) Bulk laxatives such as methylcellulose (Citrucel) and psyllium (Metamucil), if chronic constipation is causing your incontinence");
            homeRemidis.setText("Heat stroke is a medical emergency. Seek immediate emergency care if you think someone might have heat stroke.\n" +
                    "Lower Body Temperature While Waiting for Emergency Services to Arrive.\n" +
                    "Get the person into air conditioning if possible or out of the sun and into the shade.\n" +
                    "Spray the person with cool water, or apply cold wet cloths or ice packs to the armpits, neck, and groin. Fan air across the person to increase cooling. These methods help cool the person more quickly.\n");
            doctorsgoing.setText("Do not give the person anything to drink if the person is not alert or is vomiting.\n" +
                    "Restore Warmth Slowly\n.Seek Doctors Help");
        }
        if (getDocName.equals("Hypothermia")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("Hypothermia is a medical emergency that occurs when your body loses heat faster than it can produce heat, causing a dangerously low body temperature. Normal body temperature is around 98.6 F (37 C). Hypothermia (hi-poe-THUR-me-uh) occurs as your body temperature falls below 95 F (35 C).\n" +
                    "\n" +
                    "When your body temperature drops, your heart, nervous system and other organs can't work normally. Left untreated, hypothermia can eventually lead to complete failure of your heart and respiratory system and eventually to death.\n" +
                    "\n" +
                    "Hypothermia is often caused by exposure to cold weather or immersion in cold water. Primary treatments for hypothermia are methods to warm the body back to a normal temperature.");
            dis_sym.setText("Shivering.\n" +
                    "Slurred speech or mumbling.\n" +
                    "Slow, shallow breathing.\n" +
                    "Weak pulse.\n" +
                    "Clumsiness or lack of coordination.\n" +
                    "Drowsiness or very low energy.\n" +
                    "Confusion or memory loss.\n" +
                    "Loss of consciousness.");
            what_to_do.setText("Do not immerse the person in warm water. Rapid warming can cause heart arrhythmia.");
            homeRemidis.setText("Get the person indoors.\n" +
                    "Remove wet clothing and dry the person off, if needed.\n" +
                    "Warm the person's trunk first, not hands and feet. Warming extremities first can cause shock.\n" +
                    "Warm the person by wrapping him or her in blankets or putting dry clothing on the person.\n");

            doctorsgoing.setText("Seek Doctors Help As Early As Possible");

        }
        if (getDocName.equals("Insect Bites and Stings")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("Insect bites and stings can cause an immediate skin reaction. The bite from fire ants and the sting from bees, wasps, and hornets are most often painful. Bites caused by mosquitoes, fleas, and mites are more likely to cause itching than pain.\n" +
                    "\n" +
                    "Insect and spider bites cause more deaths from venom reactions than bites from snakes..");
            dis_sym.setText("ymptoms depend on the type of bite or sting. They may include:\n" +
                    "\n" +
                    "Pain\n" +
                    "Redness\n" +
                    "Swelling\n" +
                    "Itching\n" +
                    "Burning\n" +
                    "Numbness\n" +
                    "Tingling\n" +
                    "Some people have severe, life-threatening reactions to bee stings or insect bites. This is called anaphylactic shock. This condition can occur very quickly and lead to rapid death if not treated quickly.\n" +
                    "\n" +
                    "Symptoms of anaphylaxis can occur quickly and affect the whole body. They include:\n" +
                    "\n" +
                    "Chest pain\n" +
                    "Face or mouth swelling\n" +
                    "Difficulty swallowing\n" +
                    "Difficulty breathing\n" +
                    "Fainting or lightheadedness\n" +
                    "Abdominal pain or vomiting\n" +
                    "Rash or flushing");
            what_to_do.setText("Scrape the area with the edge of a credit card or straight edge object to remove it.\n" +
                    "Don't pinch the stinger or use tweezers -- that can inject more venom\n");
            homeRemidis.setText("Control Swelling:\n" +
                    "Ice the area.\n" +
                    "If you were stung on your arm or leg, elevate it.\n" +
                    "Remove any tight-fitting jewelry from the area of the sting. As it swells, rings or bracelets might be difficult to remove.\n" +
                    " Treat Symptoms:\n" +
                    "For pain, take an over-the-counter painkiller like acetaminophen or ibuprofen. Do not give aspirin to anyone under age 19.\n" +
                    "For itchiness, take an antihistamine. You can also apply a mixture of baking soda and water or calamine lotion.\n");
            doctorsgoing.setText("Seek medical help as soon as possible.");
        }
        if (getDocName.equals("")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("A strain is a stretching or tearing of muscle or tendon. A tendon is a fibrous cord of tissue that connects muscles to bones. Strains often occur in the lower back and in the hamstring muscle in the back of your thigh.");
            dis_sym.setText("  Swelling, bruising, or redness due to the injury.\n" +
                    "Pain at rest.\n" +
                    "Pain when the specific muscle or the joint in relation to that muscle is used.\n" +
                    "Weakness of the muscle or tendons.\n" +
                    "Inability to use the muscle at all");
            what_to_do.setText("Apply ice for 20 minutes each two to three hours for the first few days until the heat comes out of the injury. Ice should also help to reduce your pain and swelling in traumatic soft tissue injuries, such as ligament sprains, muscle tears or bruising");
            homeRemidis.setText("Control Swelling and Prevent Further Injury:\n" +
                    "Protect by applying an elastic bandage, sling, or splint.\n" +
                    "Rest the muscle for at least a day.\n" +
                    "Ice immediately, and continue to ice for 10 to 15 minutes every hour, for 2-3 days.\n" +
                    "Compress by gently wrapping with an Ace or other elastic bandage. (Don't wrap tightly.)\n" +
                    "Elevate injured area above the person's heart level, if possible, for at least 24 hours.\n" +
                    " Manage Pain and Inflammation:\n" +
                    "Take an over-the-counter pain medication like aspirin or ibuprofen (Advil, Motrin). Do not give aspirin to anyone under age 18.\n");
            doctorsgoing.setText("Trouble breathing or dizziness\n" +
                    "Extreme muscle weakness\n" +
                    "A high fever and stiff neck");
        }
        if (getDocName.equals("Nose-bleeding")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("A nosebleed, also known as epistaxis, is the common occurrence of bleeding from the nose. It is usually noticed when the blood drains out through the nostrils. There are two types: anterior (the most common), and posterior (less common, more likely to require medical attention).");
            what_to_do.setText("To stop a nosebleed: sit down and firmly pinch the soft part of your nose, just above your nostrils, for at least 10-15 minutes. lean forward and breathe through your mouth – this will drain blood down your nose instead of down the back of your throat.");
            homeRemidis.setText("Control Swelling and Prevent Further Injury:\n" +
                    "Protect by applying an elastic bandage, sling, or splint.\n" +
                    "Rest the muscle for at least a day.\n" +
                    "Ice immediately, and continue to ice for 10 to 15 minutes every hour, for 2-3 days.\n" +
                    "Compress by gently wrapping with an Ace or other elastic bandage. (Don't wrap tightly.)\n" +
                    "Elevate injured area above the person's heart level, if possible, for at least 24 hours.\n" +
                    " Manage Pain and Inflammation:\n" +
                    "Take an over-the-counter pain medication like aspirin or ibuprofen (Advil, Motrin). Do not give aspirin to anyone under age 18.\n");
            doctorsgoing.setText("See a doctor ");

        }
        if (getDocName.equals("Nausea and Vomiting")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("Nausea and vomiting are common signs and symptoms that can be caused by numerous conditions. Nausea and vomiting most often are due to viral gastroenteritis — often mistakenly called stomach flu — or the morning sickness of early pregnancy.");
            dis_sym.setText(" Motion sickness or seasickness\n" +
                    "Early stages of pregnancy (nausea occurs in approximately 50%-90% of all pregnancies; vomiting in 25%-55%)\n" +
                    "Medication-induced vomiting\n" +
                    "Intense pain\n" +
                    "Emotional stress (such as fear)\n" +
                    "Gallbladder disease\n" +
                    "Food poisoning\n" +
                    "Infections (such as the \"stomach flu\")\n" +
                    "Overeating\n" +
                    "A reaction to certain smells or odors\n" +
                    "Heart attack\n" +
                    "Concussion or brain injury\n" +
                    "Brain tumor\n" +
                    "Ulcers\n" +
                    "Some forms of cancer\n" +
                    "Bulimia or other psychological illnesses\n" +
                    "Gastroparesis or slow stomach emptying (a condition that can be seen in people with diabetes)\n" +
                    "Ingestion of toxins or excessive amounts of alcohol");
            what_to_do.setText("Drink water, sports drinks, or broths. ...\n" +
                    "Eat as tolerated, but only light, bland foods, such as crackers or plain bread to begin with. ...\n" +
                    "Stay away from fried or greasy foods.\n" +
                    "Steer clear of sweets.\n" +
                    "Eat small meals and eat them slowly.\n" +
                    "Rest a while after eating with your head elevated..");
            homeRemidis.setText("Stop the Bleeding\n" +
                    "Have the person sit up straight and lean forward slightly. Don't have the person lie down or tilt the head backward.\n" +
                    "With thumb and index finger, firmly pinch the nose just below the bone up against the face.\n" +
                    "Apply pressure for 5 minutes. Time yourself with a clock.\n" +
                    "If bleeding continues after 5 minutes, repeat the process.\n");
            doctorsgoing.setText("See a doctor as soon as possible");

        }
        if (getDocName.equals("Sprain")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("A sprain is a stretching or tearing of ligaments — the tough bands of fibrous tissue that connect two bones together in your joints. The most common location for a sprain is in your ankle. A strain is a stretching or tearing of muscle or tendon. A tendon is a fibrous cord of tissue that connects muscles to bones.");
            dis_sym.setText(" Pain.\n" +
                    "Swelling.\n" +
                    "Bruising.\n" +
                    "Not being able to move or use the joint.");
            what_to_do.setText("Control Swelling:\n" +
                    "Rest the sprained or strained area. If necessary, use a sling for an arm injury or crutches for a leg or foot injury. Splint an injured finger or toe by taping it to an adjacent finger or toe.\n" +
                    "Ice for 20 minutes every hour. Never put ice directly against the skin or it may damage the skin. Use a thin towel for protection.\n" +
                    "Compress by wrapping an elastic (Ace) bandage or sleeve lightly (not tightly) around the joint or limb. Specialized braces, such as for the ankle, can work better than an elastic bandage for removing the swelling\n");
            homeRemidis.setText("Elevate the area above heart level if possible.\n" +
                    " Manage Pain and Inflammation:\n" +
                    "Give an over-the-counter NSAID (non-steroidal anti-inflammatory drug) like ibuprofen (Advil, Motrin), acetaminophen (Tylenol), or aspirin. Do not give aspirin to anyone under age 19.\n");
            doctorsgoing.setText("See a Doctor:\n" +
                    "All but the most minor strains and sprains should be evaluated by a doctor. Consult a doctor as soon as possible if there are symptoms of a possible broken bone\n");

        }
        if (getDocName.equals("Scars")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            dis_name.setText(getDocName);
            dis_desc.setText("Types Of Scarring. Scars form when wounds heal from causes like burns, surgery, accidents, insect bites, and acne to name a few. Scar formation is a balance between the building up of collagen and the degradation of collagen in what are called healing cascades");
            dis_sym.setText("Scars occur at the site of tissue damage and appear as firm red to purple fibrous tissue that over time usually becomes flatter and lighter in color.");
            what_to_do.setText("    Over-the-counter or prescription creams, ointments, or gels. These products can be used to treat scars that are caused by cuts or other injuries or wounds. ...\n" +
                    "    Surgical removal or treatment. There are many options to treat deeper scars depending on your particular case. ...\n" +
                    "    Injections.");

        }


    }

    //for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
