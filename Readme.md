# Architectural Patterns — University Management

## 1. Role-Based Activity Routing

Every authenticated session resolves to one of two root activities.
`LoginActivity` reads the role stored in Firebase DB and writes it to
`SharedPreferences`, then starts the correct activity.

- Admin path: `LoginActivity` → `MainActivity` (management hub)
- User path: `LoginActivity` → `DashboardActivity` (student hub)

The role is re-read from `preferences.java` at any point that needs it.
Relevant files: `LoginActivity.java:~60-90`, `preferences.java`, `MainActivity.java:~1-30`, `studentapp/DashboardActivity.java:~1-30`.

---

## 2. Model / Adapter / RecyclerView Triple

Every list in the app follows the same three-file pattern:

```
<Feature>Data.java       — POJO with fields + getters/setters
<Feature>Adapter.java    — RecyclerView.Adapter, binds Data to ViewHolder
<Fragment|Activity>.java — attaches adapter, opens Firebase listener
```

Examples across modules:
- `notice/NoticeData.java` + `notice/NoticeAdapter.java` + `notice/DeleteNoticeActivity.java`
- `notice/ShowNoticeData.java` + `studentapp/ui/notice/ShowNoticeAdapter.java` + `studentapp/ui/notice/NoticeFragment.java`
- `faculty/TeacherData.java` + `faculty/TeacherAdapter.java` + `faculty/UpdateFaculty.java`
- `studentapp/ebook/EbookData.java` + `studentapp/ebook/EbookAdapter.java` + `studentapp/ebook/EbookActivity.java`

When adding a new list feature, create all three files following this pattern.

---

## 3. Firebase ValueEventListener Pattern

All data fetching attaches a `ValueEventListener` to a `DatabaseReference`.
The listener calls `notifyDataSetChanged()` after repopulating a local list.
No pagination or lazy loading — full node is always fetched.

Pattern (seen in every Adapter and Fragment):
```
ref.addValueEventListener(new ValueEventListener() {
    onDataChange → clear list, iterate snapshot, add to list, notifyDataSetChanged()
    onCancelled  → log/toast error
});
```

Relevant files: `NoticeAdapter.java`, `ShowNoticeAdapter.java`, `TeacherAdapter.java`,
`EbookAdapter.java`, `FacultyFragment.java`.

---

## 4. Two-Step Firebase Upload (Storage → Database)

File uploads (images, PDFs) always use the same two-step sequence:

1. Upload binary to **Firebase Storage** → get download URL from `TaskSnapshot`
2. In the `onSuccess` callback, write metadata (title, URL, timestamp) to **Firebase Realtime Database**

This ensures the database record is only written when the file is actually available.

Relevant files: `notice/UploadNotice.java`, `UploadPdfActivity.java`, `faculty/AddTeacher.java`.

---

## 5. Activity-as-Hub / Fragment Navigation (Student Side)

`DashboardActivity` owns a `BottomNavigationView` and a `NavController`.
Each tab is a Fragment. All inter-fragment navigation goes through the
Navigation Component graph (`res/navigation/`), not manual `FragmentTransaction` calls.

The admin side (`MainActivity`) uses direct `Intent` launches instead —
no fragment navigation there.

Relevant files: `studentapp/DashboardActivity.java`, `res/navigation/mobile_navigation.xml`,
`studentapp/ui/home/HomeFragment.java`, `studentapp/ui/notice/NoticeFragment.java`,
`studentapp/ui/faculty/FacultyFragment.java`, `studentapp/ui/about/AboutFragment.java`.

---

## 6. SharedPreferences Session via preferences.java

`preferences.java` is a thin static wrapper around `SharedPreferences`.
It stores login state and user role. Any activity that needs to know the
current user role calls into this class rather than reading `SharedPreferences` directly.

Used in: `LoginActivity.java`, `MainActivity.java`, `DashboardActivity.java`.

---

## 7. Shimmer as Loading Placeholder

`EbookActivity` uses Facebook Shimmer (`ShimmerFrameLayout`) while the
Firebase list is being fetched. The shimmer is shown immediately on launch,
then hidden and replaced by the RecyclerView once `onDataChange` fires.

This pattern is only applied in the eBook module but should be reused for
any future list screen that fetches remote data.

Relevant file: `studentapp/ebook/EbookActivity.java`.

---

## 8. Committee-Scoped Faculty Management

Faculty is divided into exactly 4 committees. `UpdateFaculty.java` acts as
a router: selecting a committee opens `AddTeacher`/`UpdateTeacherActivity`
scoped to that committee's Firebase node. Each committee has a fixed member cap
enforced in the UI (96, 48, or 48 depending on committee).

Relevant files: `faculty/UpdateFaculty.java`, `faculty/AddTeacher.java`,
`faculty/UpdateTeacherActivity.java`, `faculty/TeacherData.java`.
