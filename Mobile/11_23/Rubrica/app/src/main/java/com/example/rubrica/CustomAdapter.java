package com.example.rubrica;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contatto> {
    private final LayoutInflater inflater;

    public CustomAdapter(Context context, int resource, List<Contatto> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            Log.d("DEBUG", "Inflating view");
            convertView = inflater.inflate(R.layout.list_element, null);
        }

        Contatto contatto = getItem(position);

        Log.d("DEBUG", "Contact contatto = " + contatto);

        TextView name;
        TextView phone;
        ImageView picture;

        name = convertView.findViewById(R.id.name);
        phone = convertView.findViewById(R.id.phone);
        picture = convertView.findViewById(R.id.picture);

        if (contatto != null) {
            name.setText(contatto.getName());
            phone.setText(contatto.getPhone());
            picture.setImageDrawable(contatto.getPicture());

            name.setTag(position);
            phone.setTag(position);
            picture.setTag(position);
        }

        return convertView;
    }
}
