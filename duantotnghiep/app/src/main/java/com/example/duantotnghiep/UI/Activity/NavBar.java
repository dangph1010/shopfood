package com.example.duantotnghiep.UI.Activity;

public class NavBar {
//    int startingPosition;
//    BottomNavigationView bottomNav;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //Load home fragment
////        loadFragment(new HomeFragment(), 1);
//        //Bottom Navagation
//        bottomNav = findViewById(R.id.bottom_nav);
//        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment selectfrg = null;
//                int newPosition = 0;
//                switch (item.getItemId()) {
//                    case R.id.home:
//                        selectfrg = new HomeFragment();
//                        newPosition = 1 ;
//                        break;
//                    case R.id.cart:
//                        selectfrg = new CartFragment();
//                        newPosition = 2 ;
//                        break;
//                    case R.id.order:
//                        selectfrg = new OrderFragment();
//                        newPosition = 3 ;
//                        break;
//                    case R.id.account:
//                        selectfrg = new AccountFragment();
//                        newPosition = 4 ;
//                        break;
//                    default:
//                        selectfrg = new HomeFragment();
//                        newPosition = 1 ;
//                }
//                return loadFragment(selectfrg, newPosition);
//            }
//        });
//    }
//
//
//
//
//    // load fragment animation
//    private boolean loadFragment(Fragment fragment, int newPosition) {
//        if(fragment != null) {
//            if(startingPosition > newPosition) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right );
//                transaction.addToBackStack(null);
//                //frame is fragment we want to load
//                transaction.replace(R.id.frame, fragment);
//                transaction.commit();
//            }
//            if(startingPosition < newPosition) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
//                transaction.addToBackStack(null);
//                transaction.replace(R.id.frame, fragment);
//                transaction.commit();
//            }
//            startingPosition = newPosition;
//            return true;
//        }
//
//        return false;
//    }
}
