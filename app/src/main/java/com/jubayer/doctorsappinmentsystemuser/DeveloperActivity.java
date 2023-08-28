package com.jubayer.doctorsappinmentsystemuser;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeveloperActivity extends AppCompatActivity {

    private TextView facebookTV, githubTV, linkedinTV, mailTV;
    private ImageView tanim_facebookTV, tanim_github, tanim_linkedin, tanim_gmail;
    private  ImageView purnima_facebookTV, purnima_github, purnima_linkedin, purnima_gmail;
    private  ImageView proma_facebookTV, proma_githubTV, proma_linkedin, proma_gmail;
    private  ImageView sojib_facebookTV, sojib_github, sojib_linkedin, sojib_gmail;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        /*Set action bar Title */
        getSupportActionBar().setTitle("Developer List");


        /*jubayer hossain view*/
        facebookTV = findViewById(R.id.facebookTV);
        githubTV = findViewById(R.id.githubTV);
        linkedinTV = findViewById(R.id.linkedinTV);
        mailTV = findViewById(R.id.mailTV);

        /*torikul islam tanim view*/
        tanim_facebookTV = findViewById(R.id.tanim_facebookTV);
        tanim_github = findViewById(R.id.tanim_github);
        tanim_gmail = findViewById(R.id.tanim_gmail);
        tanim_linkedin = findViewById(R.id.tanim_linkedin);

        // Suraiya Aktery Purnima View
        purnima_facebookTV = findViewById(R.id.purnima_facebookTV);
        purnima_github = findViewById(R.id.purnima_github);
        purnima_linkedin = findViewById(R.id.purnima_linkedin);
        purnima_gmail = findViewById(R.id.purnima_gmail);

        // sharmila rahman proma view
        proma_facebookTV = findViewById(R.id.proma_facebookTV);
        proma_githubTV = findViewById(R.id.proma_githubTV);
        proma_linkedin = findViewById(R.id.proma_linkedin);
        proma_gmail = findViewById(R.id.proma_gmail);

        //Sojib View
        sojib_facebookTV = findViewById(R.id.sojib_facebookTV);
        sojib_github = findViewById(R.id.sojib_github);
        sojib_linkedin = findViewById(R.id.sojib_linkedin);
        sojib_gmail = findViewById(R.id.sojib_gmail);

        //*Facebook Jubayer view*/
        facebookTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getOpenFacebookIntent());
            }
        });

        githubTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent appIntent = new Intent(Intent.ACTION_VIEW);
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/jubayer282"));
                try {
                    startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                    startActivity(webIntent);
                }
            }
        });

        linkedinTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://you"));
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/jubayer-hossain-760b7623b/"));
                startActivity(intent);
            }
        });

        mailTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "jubayer.trodev@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(DeveloperActivity.this, "Failed to due " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*tanim facebook*/
        tanim_facebookTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getOpenFacebookIntenttanim());
            }
        });
        tanim_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent appIntent = new Intent(Intent.ACTION_VIEW);
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/taneemahmed786"));
                try {
                    startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                    startActivity(webIntent);
                }

            }
        });
        tanim_linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://you"));
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/torikul-islam-tanim-9217a2274/"));
                startActivity(intent);
            }
        });
        tanim_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "tanimtt210@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(DeveloperActivity.this, "Failed to due " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Suraiya Aktery Purnima
        purnima_facebookTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(getOpenFacebookIntentpurnima());
            }
        });
        purnima_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent appIntent = new Intent(Intent.ACTION_VIEW);
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/Suraiyapurnima"));
                try {
                    startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                    startActivity(webIntent);
                }
            }
        });
        purnima_linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://you"));
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/suraiya-purnima-6428ba274/"));
                startActivity(intent);
            }
        });
        purnima_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "suraiyapurnima@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(DeveloperActivity.this, "Failed to due " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //proma facebook
        proma_facebookTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getOpenFacebookIntentproma());
            }
        });
        proma_githubTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent appIntent = new Intent(Intent.ACTION_VIEW);
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/sharmilaproma56"));
                try {
                    startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                    startActivity(webIntent);
                }
            }
        });
        proma_linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://you"));
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/sharmila-proma-03184a274/"));
                startActivity(intent);
            }
        });
        proma_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "sharmilaproma41@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(DeveloperActivity.this, "Failed to due " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //sojib Facebook
        sojib_facebookTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getOpenFacebookIntentsojib());
            }
        });
        sojib_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent appIntent = new Intent(Intent.ACTION_VIEW);
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/shahriarkabirsojib"));
                try {
                    startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                    startActivity(webIntent);
                }
            }
        });
        sojib_linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://you"));
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/shahriar-kabir-56a187207"));
                startActivity(intent);
            }
        });
        sojib_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "shahriarkabir202@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(DeveloperActivity.this, "Failed to due " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

     //jubayer facebook link
     public Intent getOpenFacebookIntent() {
         try {
             getPackageManager().getPackageInfo("com.facebook.katana", 0);
             return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/mdjubayer.hossain.98434?mibextid=ZbWKwL"));
         } catch (Exception e) {
             return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/mdjubayer.hossain.98434?mibextid=ZbWKwL"));
         }
     }

    // tanim fb link
    private Intent getOpenFacebookIntenttanim() {
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ifratahmed.tamim?mibextid=ZbWKwL"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ifratahmed.tamim?mibextid=ZbWKwL"));
        }
    }

    // purnima fb link
    private Intent getOpenFacebookIntentpurnima() {
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100080431145249&mibextid=Zb\n" +
                    "WKwL/"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100080431145249&mibextid=Zb\n" +
                    "WKwL/"));
        }
    }
    //proma fb link

    private Intent getOpenFacebookIntentproma() {
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/sharmila.proma.1?mibextid=ZbWKwL/"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/sharmila.proma.1?mibextid=ZbWKwL/"));
        }
    }

    // Sojib Fb link
    private Intent getOpenFacebookIntentsojib() {
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/shahriar.kabirsojib?mibextid=ZbWKwL/"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/shahriar.kabirsojib?mibextid=ZbWKwL/"));
        }
    }

}