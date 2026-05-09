package com.example.kashta_kala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.ui.draw.scale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                KashtaKalaApp()
            }
        }
    }
}

data class FurnitureItem(
    val title: String,
    val description: String,
    val image: Int,
    val category: String,
    val price: String
)

data class InvoiceData(
    val furnitureName: String,
    val materialCost: String,
    val laborCost: String,
    val totalCost: String,
    val date: String,
    val customerName: String = ""
)

@Composable
fun KashtaKalaApp() {

    var currentScreen by remember {
        mutableStateOf("splash")
    }

    LaunchedEffect(Unit) {
        delay(2000)
        currentScreen = "login"
    }

    when (currentScreen) {

        "splash" -> SplashScreen()

        "login" -> LoginScreen(
            onLogin = {
                currentScreen = "home"
            },
            onCreateAccount = {
                currentScreen = "register"
            }
        )

        "register" -> RegisterScreen(
            onRegister = {
                currentScreen = "home"
            }
        )

        "home" -> HomeScreen()
    }
}

@Composable
fun SplashScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.bed_img),
            contentDescription = "Logo",
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        val infiniteTransition = rememberInfiniteTransition(label = "")

        val scale by infiniteTransition.animateFloat(
            initialValue = 0.95f,
            targetValue = 1.05f,
            animationSpec = infiniteRepeatable(
                animation = tween(1500),
                repeatMode = RepeatMode.Reverse
            ),
            label = ""
        )
        Text(
            text = "Kashta-Kala",
            modifier = Modifier.scale(scale),
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Premium Furniture Designs",
            color = Color.Gray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        CircularProgressIndicator(
            color = Color(0xFF6D4C41)
        )
    }
}

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onCreateAccount: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Kashta-Kala",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Premium Furniture Designs",
            color = Color.Gray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                onLogin()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

            shape = RoundedCornerShape(16.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6D4C41)
            )
        ) {

            Text(
                "Login",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Create New Account",
            color = Color(0xFF5D4037),
            fontWeight = FontWeight.Bold,

            modifier = Modifier.clickable {
                onCreateAccount()
            }
        )
    }
}

@Composable
fun RegisterScreen(
    onRegister: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Create Account",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037)
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                onRegister()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

            shape = RoundedCornerShape(16.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6D4C41)
            )
        ) {

            Text(
                "Register",
                fontSize = 18.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    var selectedBottom by remember {
        mutableStateOf("Home")
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf("All")
    }

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    val categories = listOf(
        "All",
        "Beds",
        "Cabinets",
        "Chairs",
        "Sofas",
        "Tables"
    )

    val furnitureList = listOf(

        FurnitureItem(
            "Modern Bed",
            "Luxury king size wooden bed",
            R.drawable.bed_img,
            "Beds",
            "₹45,000"
        ),

        FurnitureItem(
            "Classic Bed",
            "Premium wooden bed design",
            R.drawable.bed2_img,
            "Beds",
            "₹40,000"
        ),

        FurnitureItem(
            "Designer Bed",
            "Elegant designer bed",
            R.drawable.bed3_img,
            "Beds",
            "₹50,000"
        ),

        FurnitureItem(
            "Royal Bed",
            "Luxury royal wooden bed",
            R.drawable.bed4_img,
            "Beds",
            "₹60,000"
        ),

        FurnitureItem(
            "Modern Cabinet",
            "Premium wooden storage cabinet",
            R.drawable.cabinet_img,
            "Cabinets",
            "₹22,000"
        ),

        FurnitureItem(
            "Luxury Cabinet",
            "Elegant luxury cabinet design",
            R.drawable.cabinet2_img,
            "Cabinets",
            "₹28,000"
        ),

        FurnitureItem(
            "Classic Cabinet",
            "Traditional wooden cabinet",
            R.drawable.cabinet3_img,
            "Cabinets",
            "₹24,000"
        ),

        FurnitureItem(
            "Designer Cabinet",
            "Modern designer cabinet",
            R.drawable.cabinet4_img,
            "Cabinets",
            "₹30,000"
        ),

        FurnitureItem(
            "Wooden Chair",
            "Modern chair design",
            R.drawable.chair_img,
            "Chairs",
            "₹12,000"
        ),

        FurnitureItem(
            "Classic Chair",
            "Traditional wooden chair",
            R.drawable.chair2_img,
            "Chairs",
            "₹9,000"
        ),

        FurnitureItem(
            "Office Chair",
            "Comfortable office chair",
            R.drawable.chair3_img,
            "Chairs",
            "₹15,000"
        ),

        FurnitureItem(
            "Luxury Chair",
            "Premium luxury chair",
            R.drawable.chair4_img,
            "Chairs",
            "₹18,000"
        ),

        FurnitureItem(
            "Modern Sofa",
            "Comfortable premium sofa",
            R.drawable.sofa_img,
            "Sofas",
            "₹30,000"
        ),

        FurnitureItem(
            "Luxury Sofa",
            "Elegant sofa set",
            R.drawable.sofa2_img,
            "Sofas",
            "₹35,000"
        ),

        FurnitureItem(
            "Classic Sofa",
            "Stylish classic sofa",
            R.drawable.sofa3_img,
            "Sofas",
            "₹32,000"
        ),

        FurnitureItem(
            "Royal Sofa",
            "Luxury royal sofa",
            R.drawable.sofa4_img,
            "Sofas",
            "₹40,000"
        ),

        FurnitureItem(
            "Dining Table",
            "Wooden dining table",
            R.drawable.table_img,
            "Tables",
            "₹25,000"
        ),

        FurnitureItem(
            "Premium Table",
            "Luxury wooden table",
            R.drawable.table2_img,
            "Tables",
            "₹28,000"
        ),

        FurnitureItem(
            "Classic Table",
            "Traditional dining table",
            R.drawable.table3_img,
            "Tables",
            "₹26,000"
        ),

        FurnitureItem(
            "Office Table",
            "Premium office workspace table",
            R.drawable.table4_img,
            "Tables",
            "₹18,000"
        )
    )

    val favoriteItems = remember {
        mutableStateListOf<FurnitureItem>()
    }

    val cartItems = remember {
        mutableStateListOf<FurnitureItem>()
    }

    val savedInvoices = remember {
        mutableStateListOf<String>()
    }

    val filteredList = furnitureList.filter {

        val matchesSearch =
            it.title.contains(searchText, ignoreCase = true)

        val matchesCategory =
            selectedCategory == "All" ||
                    it.category == selectedCategory

        matchesSearch && matchesCategory
    }

    ModalNavigationDrawer(

        drawerState = drawerState,

        drawerContent = {

            ModalDrawerSheet {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Kashta-Kala",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6D4C41),
                    modifier = Modifier.padding(16.dp)
                )

                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = false,
                    onClick = {
                        selectedBottom = "Home"

                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Favorites") },
                    selected = false,
                    onClick = {
                        selectedBottom = "Favorites"

                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Cart") },
                    selected = false,
                    onClick = {
                        selectedBottom = "Cart"

                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Portfolio") },
                    selected = false,
                    onClick = {
                        selectedBottom = "Portfolio"

                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Saved Quotes") },
                    selected = false,
                    onClick = {
                        selectedBottom = "Invoice"

                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Logout") },
                    selected = false,
                    onClick = {

                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            }
        }
    ) {

        Scaffold(

            topBar = {

                TopAppBar(

                    title = {
                        Text("Kashta-Kala")
                    },

                    navigationIcon = {

                        IconButton(
                            onClick = {

                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            },

            containerColor = Color.White,

            bottomBar = {

                NavigationBar(
                    containerColor = Color.White
                ) {

                    NavigationBarItem(
                        selected = selectedBottom == "Home",
                        onClick = {
                            selectedBottom = "Home"
                        },
                        icon = {
                            Icon(Icons.Default.Home, null)
                        },
                        label = {
                            Text("Home")
                        }
                    )

                    NavigationBarItem(
                        selected = selectedBottom == "Favorites",
                        onClick = {
                            selectedBottom = "Favorites"
                        },
                        icon = {
                            Icon(Icons.Default.Favorite, null)
                        },
                        label = {
                            Text("Favorites")
                        }
                    )

                    NavigationBarItem(
                        selected = selectedBottom == "Cart",
                        onClick = {
                            selectedBottom = "Cart"
                        },

                        icon = {

                            BadgedBox(
                                badge = {

                                    if (cartItems.isNotEmpty()) {

                                        Badge {
                                            Text("${cartItems.size}")
                                        }
                                    }
                                }
                            ) {

                                Icon(
                                    Icons.Default.ShoppingCart,
                                    contentDescription = null
                                )
                            }
                        },

                        label = {
                            Text("Cart")
                        }
                    )

                    NavigationBarItem(
                        selected = selectedBottom == "Portfolio",
                        onClick = {
                            selectedBottom = "Portfolio"
                        },
                        icon = {
                            Icon(Icons.Default.PhotoCamera, null)
                        },
                        label = {
                            Text("Portfolio")
                        }
                    )

                    NavigationBarItem(
                        selected = selectedBottom == "Invoice",
                        onClick = {
                            selectedBottom = "Invoice"
                        },
                        icon = {
                            Icon(Icons.Default.Receipt, null)
                        },
                        label = {
                            Text("Invoice")
                        }
                    )
                }
            }

        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
            ) {

                when (selectedBottom) {

                    "Favorites" -> {

                        Text(
                            text = "Favorites ❤️",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )

                        favoriteItems.forEach { item ->

                            FurnitureCard(
                                item = item,
                                isFavorite = true,
                                isCartAdded = cartItems.contains(item),

                                onFavoriteClick = {
                                    favoriteItems.remove(item)
                                },

                                onCartClick = {
                                    if (!cartItems.contains(item)) {
                                        cartItems.add(item)
                                    }
                                },

                                onInvoiceGenerated = {
                                    if (!savedInvoices.contains(it)) {
                                        savedInvoices.add(it)
                                    }
                                }
                            )

                            Spacer(modifier = Modifier.height(18.dp))
                        }
                    }

                    "Cart" -> {

                        Text(
                            text = "My Cart 🛒",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )

                        cartItems.forEach { item ->

                            FurnitureCard(
                                item = item,
                                isFavorite = favoriteItems.contains(item),
                                isCartAdded = true,

                                onFavoriteClick = {

                                    if (favoriteItems.contains(item)) {
                                        favoriteItems.remove(item)
                                    } else {
                                        favoriteItems.add(item)
                                    }
                                },

                                onCartClick = {},

                                onInvoiceGenerated = {
                                    if (!savedInvoices.contains(it)) {
                                        savedInvoices.add(it)
                                    }
                                }
                            )

                            Spacer(modifier = Modifier.height(18.dp))
                        }
                    }

                    "Portfolio" -> {

                        Text(
                            text = "My Portfolio",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )

                        PortfolioCard(
                            title = "Custom Wardrobe",
                            description = "Premium wardrobe completed for client",
                            image = R.drawable.cabinet2_img
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        PortfolioCard(
                            title = "Luxury Sofa Project",
                            description = "Luxury sofa delivered successfully",
                            image = R.drawable.sofa3_img
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        PortfolioCard(
                            title = "Dining Table Project",
                            description = "Modern teakwood dining table set",
                            image = R.drawable.table2_img
                        )
                    }

                    "Invoice" -> {

                        Text(
                            text = "Saved Invoices",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )

                        if (savedInvoices.isEmpty()) {

                            Text(
                                text = "No invoices generated",
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                        savedInvoices.forEach {

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 10.dp),

                                shape = RoundedCornerShape(22.dp),

                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFF3ECF8)
                                ),

                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 5.dp
                                )
                            ) {

                                Column(
                                    modifier = Modifier.padding(20.dp)
                                ) {

                                    Text(
                                        text = "Modern Bed",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF4E342E)
                                    )

                                    Spacer(modifier = Modifier.height(18.dp))

                                    InvoiceRow(
                                        title = "Material Cost",
                                        value = "₹ 12000"
                                    )

                                    InvoiceRow(
                                        title = "Labor Cost",
                                        value = "₹ 3000"
                                    )

                                    InvoiceRow(
                                        title = "GST",
                                        value = "₹ 1592"
                                    )

                                    HorizontalDivider(
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        thickness = 1.dp,
                                        color = Color.LightGray
                                    )

                                    InvoiceRow(
                                        title = "Total Amount",
                                        value = "₹ 16592",
                                        isTotal = true
                                    )

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Text(
                                        text = "Date : 05-09-2026",
                                        color = Color.Gray,
                                        fontSize = 14.sp
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = "Customer : Kavya",
                                        color = Color.Gray,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }

                if (selectedBottom == "Home") {
                    Spacer(modifier = Modifier.height(16.dp))

                    val bannerList = listOf(
                        "🔥 50% OFF on Custom Furniture",
                        "🪵 Premium Teak Wood Collection",
                        "✨ Modern Interior Designs",
                        "🚚 Free Delivery Available"
                    )

                    var currentBanner by remember {
                        mutableStateOf(0)
                    }

                    LaunchedEffect(Unit) {

                        while (true) {

                            delay(2500)

                            currentBanner =
                                (currentBanner + 1) % bannerList.size
                        }
                    }

                    AnimatedContent(
                        targetState = currentBanner,
                        transitionSpec = {

                            slideInHorizontally(
                                initialOffsetX = { it }
                            ) + fadeIn() togetherWith

                                    slideOutHorizontally(
                                        targetOffsetX = { -it }
                                    ) + fadeOut()
                        },

                        label = ""
                    ) { index ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),

                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF6D4C41)
                            ),

                            shape = RoundedCornerShape(24.dp),

                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 8.dp
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(22.dp)
                            ) {

                                Text(
                                    text = bannerList[index],
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Premium handcrafted furniture designs",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),

                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Surface(
                            modifier = Modifier.size(60.dp),
                            shape = RoundedCornerShape(50.dp),
                            color = Color(0xFF6D4C41)
                        ) {

                            Box(
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text = "K",
                                    color = Color.White,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {

                            Text(
                                text = "Hello, Kavya 👋",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Welcome back",
                                color = Color.Gray
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                        },
                        label = {
                            Text("Search Furniture")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 12.dp)
                    ) {

                        categories.forEach { category ->

                            FilterChip(
                                selected = selectedCategory == category,

                                onClick = {
                                    selectedCategory = category
                                },

                                label = {
                                    Text(category)
                                },

                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    filteredList.forEach { item ->

                        FurnitureCard(
                            item = item,

                            isFavorite = favoriteItems.contains(item),

                            isCartAdded = cartItems.contains(item),

                            onFavoriteClick = {

                                if (favoriteItems.contains(item)) {
                                    favoriteItems.remove(item)
                                } else {
                                    favoriteItems.add(item)
                                }
                            },

                            onCartClick = {

                                if (!cartItems.contains(item)) {
                                    cartItems.add(item)
                                }
                            },

                            onInvoiceGenerated = {

                                if (!savedInvoices.contains(it)) {
                                    savedInvoices.add(it)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(18.dp))
                    }
                }

            }
        }
    }
}
@Composable
fun FurnitureCard(
    item: FurnitureItem,
    isFavorite: Boolean,
    isCartAdded: Boolean,
    onFavoriteClick: () -> Unit,
    onCartClick: () -> Unit,
    onInvoiceGenerated: (String) -> Unit
) {

    var visible by remember {
        mutableStateOf(false)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(
            initialOffsetY = { 120 }
        )
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .animateContentSize(),

            shape = RoundedCornerShape(22.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {

            Column {

                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = item.title,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),

                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = item.description,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = item.price,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6D4C41)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Button(
                                onClick = {
                                    showDialog = true
                                },

                                shape = RoundedCornerShape(14.dp),

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF6D4C41)
                                )
                            ) {

                                Text("Get Quote")
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Button(
                                onClick = {
                                    onCartClick()
                                },

                                enabled = !isCartAdded,

                                shape = RoundedCornerShape(14.dp)
                            ) {

                                Text(
                                    if (isCartAdded)
                                        "Added"
                                    else
                                        "Add Cart"
                                )
                            }
                        }

                        IconButton(
                            onClick = {
                                onFavoriteClick()
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,

                                tint =
                                    if (isFavorite)
                                        Color.Red
                                    else
                                        Color.Gray
                            )
                        }
                    }
                }
            }
        }

        if (showDialog) {

            EstimateDialog(
                item = item,

                onDismiss = {
                    showDialog = false
                },

                onInvoiceGenerated = {
                    onInvoiceGenerated(it)
                }
            )
        }
    }
}

@Composable
fun EstimateDialog(
    item: FurnitureItem,
    onDismiss: () -> Unit,
    onInvoiceGenerated: (String) -> Unit
) {

    val context = LocalContext.current

    var length by remember { mutableStateOf("") }
    var width by remember { mutableStateOf("") }
    var laborCost by remember { mutableStateOf("") }

    var result by remember {
        mutableStateOf("")
    }

    AlertDialog(

        onDismissRequest = {
            onDismiss()
        },

        title = {
            Text(
                text = item.title + " Quote"
            )
        },

        text = {

            Column {

                OutlinedTextField(
                    value = length,
                    onValueChange = {
                        length = it
                    },
                    label = {
                        Text("Length")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = width,
                    onValueChange = {
                        width = it
                    },
                    label = {
                        Text("Width")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = laborCost,
                    onValueChange = {
                        laborCost = it
                    },
                    label = {
                        Text("Labor Cost")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {

                        val l = length.toDoubleOrNull() ?: 0.0
                        val w = width.toDoubleOrNull() ?: 0.0
                        val labor = laborCost.toDoubleOrNull() ?: 0.0

                        val area = (l * w) / 144
                        val materialCost = area * 850
                        val total = materialCost + labor

                        result = """
    Wood Required: ${"%.2f".format(area)} sq.ft

    Material Cost: ₹ ${"%.2f".format(materialCost)}
    Labor Cost: ₹ ${"%.2f".format(labor)}

    Total Cost: ₹ ${"%.2f".format(total)}
""".trimIndent()

                        val invoice =
                            item.title +
                                    "\nTotal Amount : ₹ %.2f".format(total)

                        onInvoiceGenerated(invoice)

                        CoroutineScope(Dispatchers.IO).launch {

                            try {

                                val db = AppDatabase.getDatabase(context)

                                db.quoteDao().insertQuote(
                                    QuoteEntity(
                                        furnitureName = item.title,
                                        totalCost = "₹ %.2f".format(total)
                                    )
                                )

                            } catch (_: Exception) {

                            }
                        }
                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(14.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6D4C41)
                    )
                ) {

                    Text("Calculate & Save")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = result,
                    fontWeight = FontWeight.Bold
                )
            }
        },

        confirmButton = {

            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {

                Text("Close")
            }
        }
    )
}

@Composable
fun PortfolioCard(
    title: String,
    description: String,
    image: Int
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Column {

            Image(
                painter = painterResource(id = image),
                contentDescription = title,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),

                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = description,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(14.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8F5E9)
                    ),

                    shape = RoundedCornerShape(50.dp)
                ) {

                    Text(
                        text = "PROJECT COMPLETED",

                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),

                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
@Composable
fun InvoiceRow(
    title: String,
    value: String,
    isTotal: Boolean = false
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            fontSize = if (isTotal) 20.sp else 16.sp,
            fontWeight =
                if (isTotal)
                    FontWeight.Bold
                else
                    FontWeight.Medium
        )

        Text(
            text = value,
            fontSize = if (isTotal) 20.sp else 16.sp,
            fontWeight =
                if (isTotal)
                    FontWeight.Bold
                else
                    FontWeight.Medium,

            color =
                if (isTotal)
                    Color(0xFF2E7D32)
                else
                    Color.Black
        )
    }
}